package com.atguigu.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
//	public static<T> T strToObject(String str,Class<T> clazz){
//        ObjectMapper mapper = new ObjectMapper();
//        T t = null;
//        try {
//        t = mapper.readValue(str, clazz);
//            return t;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return t;
//    }
//
//
//    //����ת��Ϊ�ַ���
//    public static<T> String objectToStr(T t){
//        ObjectMapper mapper = new ObjectMapper();
//        String str = null;
//        try {
//            str = mapper.writeValueAsString(t);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return str;
//    }
//    @Test
//    public void testJson(){
//
//        String str = "{\"name\":\"gg\",\"pass\":\"123\"}";
//        System.out.println(str);
//        System.out.println("--------------�ַ���ת��Ϊ����ָ���-------------------");
//        Person person = strToObject(str,Person.class);
//        System.out.println(person.getName()+":"+person.getPass());
//        System.out.println("----------------����ת��Ϊ�ַ����ָ���-----------------");
//        //����ת��Ϊ�ַ���
//        String rtnString =  objectToStr(person);
//        System.out.println(rtnString);
//    }
//
//    //����ת��ΪList����
//    private static <T> List<T> strToList(String str, Class<T> clazz) {
//        JSONArray json = JSONArray.fromObject(str);
//        JSONObject object = null;
//        T t = null;
//        List<T> list = new ArrayList<>();
//        for (int i = 0; i < json.size(); i++) {
//            object = JSONObject.fromObject(json.get(i));
//            t = (T) JSONObject.toBean(object, clazz);
//            list.add(t);
//        }
//        return list;
//    }
//
//
//    public static void main(String[] args) {
//
//        String str = "[{\"name\":\"gg\",\"pass\":\"123\"},{\"name\":\"gg\",\"pass\":\"234\"},{\"name\":\"gg\",\"pass\":\"345\"}]";
//        // List<Person> list = JSONUtil.strToList(str,Person.class);
//
//        List<Person> list = strToList(str, Person.class);
//        for (Person ps : list) {
//            System.out.println(ps.getName() + ":" + ps.getPass());
//        }
//    }


}
