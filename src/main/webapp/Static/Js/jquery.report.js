/*
 *   $.Report.js v1.0.0.1
 *   用于Report的前端控件，是基于$.js框架开发需要引用$.js文件
 *   author: hfs
 *   create: 2016-06-06
 *
 *用法
 (window.loadData = function (type) {
        var option = {
            type: type,
            labels: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
            datasets: [
                { 
                    //系列标题
                    label: "销售额", 
                    //x轴的数据
                    data: [65, 81, 56, 55, 40, 59, 65, 81, 56, 55, 40, 59],
                    //x轴的扩展数据，在对应的下标可以传对象（传什么得什么）
                    items:[
                        {id:1,value:65},
                        {id:2,value:81},
                        {id:3,value:56},
                        ......
                    ]
                }
            ]
        };
        $.report.createReport("#ReportBox", option, function(){ });
    })(1);
  *
*/
(function ($, win) {
    var report = $.report = {
        createColor: function (i, a) {
            var R = 0, G = 0, B = 0;
            R = Math.round(i * 0.08 * 255);
            if (R > 255) {
                R = R % 255;
            }
            G = Math.round(i * 0.26 * 255);
            if (G > 255) {
                G = G % 255;
            }
            B = Math.round(i * 0.15 * 255);
            if (B > 255) {
                B = B % 255;
            }
            return "rgba(" + R + "," + G + "," + B + "," + a + ")";
        },
        createReport: function (container, option, callback) {
            $.getScript("/Static/js/chart.js", function () {
                var myCanvas = document.createElement("canvas"),
                    mychart = $(container);
                myCanvas.setAttribute("width", mychart.width() + "px");
                myCanvas.setAttribute("height", mychart.height() + "px");
                mychart.html(myCanvas);

                var ctx = myCanvas.getContext("2d");

                var data = {
                    labels: option.labels || [],
                    datasets: [],
                    items: option.items || []
                };

                $(option.datasets).each(function (n, i) {
                    var dataset = {};
                    dataset.fill = false;
                    dataset.label = n.label || "";
                    dataset.data = n.data || [];

                    //颜色处理
                    if (option.type == 1) {
                        dataset.backgroundColor = report.createColor(i + 1, 0.5);
                    } else if (option.type == 2) {
                        dataset.backgroundColor = report.createColor(i + 1, 0.5);
                        dataset.borderColor = report.createColor(i + 1, 0.4);
                        dataset.pointBorderColor = report.createColor(i + 1, 0.7);
                        dataset.pointBackgroundColor = report.createColor(i + 1, 0.5);
                        dataset.pointBorderWidth = 1;
                    } else if (option.type == 3) {
                        dataset.backgroundColor = [];
                        dataset.hoverBackgroundColor = [];
                        for (var d = 1; d < dataset.data.length; d++) {
                            dataset.backgroundColor.push(report.createColor(d + 1, 0.5));
                            dataset.hoverBackgroundColor.push(report.createColor(d + 1, 0.5));
                        }
                    }

                    data.datasets.push(dataset);
                });

                var t = "bar";
                switch (option.type) {
                    case 1: t = "bar"; break;
                    case 2: t = "line"; break;
                    case 3: t = "pie"; break;
                }
                new Chart(ctx, {
                    type: t,
                    data: data,
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        },
                        onClick: function () {
                            var event = arguments[0];
                            //console.log(event);
                            if (callback) {
                                var params = this.getElementAtEvent(event);
                                //console.log(params);
                                if (params.length > 0) {
                                    var _datasetIndex = params[0]._datasetIndex;
                                    var _index = params[0]._index;
                                    var _chart = params[0]._chart;
                                    var _labels = _chart.config.data.labels;
                                    var _datasets = _chart.config.data.datasets;
                                    var _items = _chart.config.data.items;
                                    var _series = _datasets[_datasetIndex].label;
                                    var _value = _datasets[_datasetIndex].data[_index];

                                    var model = {};
                                    model.series = _series;
                                    model.datasetIndex = _datasetIndex;
                                    model.label = _labels[_index];
                                    model.value = _value;
                                    model.item = _items[_index];
                                    model.index = _index;
                                    //model.labels = _labels;
                                    //model.datasets = _datasets;
                                    //console.log(model);
                                    callback(model);
                                }
                            }
                        }
                    }
                });
            });
        }
    };
}(jquery, window));