package fire.web.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnumHelperUtil{
	/**
	 * indexOf,����Ĳ���ordinalָ������Ҫ��ö��ֵ���趨��ö�����е�˳����0��ʼ
	 * T
	 * @param clazz
	 * @param ordinal
	 * @return
	 */
	public static <T extends Enum<T>> T indexOf(Class<T> clazz, int ordinal){
		return (T) clazz.getEnumConstants()[ordinal];
	}
	/**
	 * nameOf,����Ĳ���nameָ����ö��ֵ�����ƣ�һ���Ǵ�д���»��ߵ�
	 * T
	 * @param clazz
	 * @param name
	 * @return Enum T
	 */
	public static <T extends Enum<T>> T nameOf(Class<T> clazz, String name){
		
		return (T) Enum.valueOf(clazz, name);
	}
	/**
	 * ʹ��ö�����Ͷ�Ӧ��typeCode��ȡö������
	 * T
	 * @param clazz    ö�����class
	 * @param getTypeCodeMethodName  �����typeCode��get����
	 * @param typeCode  �����typeCodeֵ���������ΪString����
	 * @return
	 */
	public static <T extends Enum<T>> T getByStringTypeCode(Class<T> clazz,String getTypeCodeMethodName, String typeCode){
		T result = null;
		try{
			T[] arr = clazz.getEnumConstants();
			Method targetMethod = clazz.getDeclaredMethod(getTypeCodeMethodName);
			String typeCodeVal = null;
			for(T entity:arr){
				typeCodeVal = targetMethod.invoke(entity).toString();
				if(typeCodeVal.contentEquals(typeCode)){
					result = entity;
					break;
				}
			}			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ʹ��ö�����Ͷ�Ӧ��typeCode��ȡö������
	 * T
	 * @param clazz    ö�����class
	 * @param getTypeCodeMethodName  �����typeCode��get����
	 * @param typeCode  �����typeCodeֵ���������ΪInteger����
	 * @return
	 */
	public static <T extends Enum<T>> T getByIntegerTypeCode(Class<T> clazz,String getTypeCodeMethodName, Integer typeCode){
		T result = null;
		try{
			T[] arr = clazz.getEnumConstants();
			Method targetMethod = clazz.getDeclaredMethod(getTypeCodeMethodName);
			Integer typeCodeVal = null;
			for(T entity:arr){
				typeCodeVal = Integer.valueOf(targetMethod.invoke(entity).toString());
				if(typeCodeVal.equals(typeCode)){
					result = entity;
					break;
				}
			}			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * ʹ��ö�����Ͷ�Ӧ��typeName��ȡö������
	 * T
	 * @param clazz   ö�����class 
	 * @param getTypeNameMethodName  �����typeName��get����
	 * @param typeName �����typeNameֵ���������ΪString����
	 * @return
	 */
	public static <T extends Enum<T>> T getByStringTypeName(Class<T> clazz,String getTypeNameMethodName, String typeName){
		T result = null;
		try{
			T[] arr = clazz.getEnumConstants();
			Method targetMethod = clazz.getDeclaredMethod(getTypeNameMethodName);
			String typeNameVal = null;
			for(T entity:arr){
				typeNameVal = targetMethod.invoke(entity).toString();
				if(typeNameVal.contentEquals(typeName)){
					result = entity;
					break;
				}
			}			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return result;
	}
}
