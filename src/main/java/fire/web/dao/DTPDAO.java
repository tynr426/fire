package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.web.entity.Devicetypeparameter;


public interface DTPDAO {
	//ͨ��DTPname��ѯDTP
	public Devicetypeparameter getDTPByDescription(
			@Param("description")String description,
			@Param("id")int id );
	//��ѯDTP����
	public List<Devicetypeparameter> findAll();
	//�޸�
	public int updateDevicetypeparameter(Devicetypeparameter dtp);
	//ͨ��id��ѯ
	public Devicetypeparameter findById(int Id);
	//���
	public int addDTP(Devicetypeparameter dtp);
	//ɾ��
	public int delete(Integer Id);
	//��ѯ����
	public int findDevicetypeparameterCount();
	//��ҳ��ѯ
	public List<Devicetypeparameter> findByLimit(
			@Param("deviceTypeId")int deviceTypeId,
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	//�޸�״ֵ̬
	public int updateStatus(Devicetypeparameter dtp);
}
