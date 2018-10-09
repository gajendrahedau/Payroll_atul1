package com.cg.project.collectiondemo;
import java.util.ArrayList;
import java.util.Collections;
import com.cg.project.beans.Associate;
public class ListClassesDemo {
	public static void arrayListClassWork(){
		ArrayList<String>strList=new ArrayList<>();
		
//		//insert
//		strList.add("Ankit");
//		strList.add("Atul");
//		strList.add("Anand");
//		
//		//search
//		System.out.println(strList.contains("Atul"));
//		
//		//sorting
//				
		
		ArrayList<Associate>associateList=new ArrayList<>();
		
		//insert
		associateList.add(new Associate(101,15000,"Ankit"));
		associateList.add(new Associate(103,16900,"Atul"));
		associateList.add(new Associate(102,17000,"Anand"));
		
		//search
		Associate associateToBeSearch=new Associate(102, 17000, "Anand");
		System.out.println(associateList.indexOf(associateToBeSearch));
		System.out.println(associateList.contains(associateToBeSearch));
		
		/*iteration(.equals can be used when type is of class for ex string or integer
		, when type is int use == )*/
		for(Associate associate: associateList){
			if(associate.getAssociateId()==103&&associate.getName().equals("Atul"));
		}
		
		//sort
		Collections.sort(associateList);
		for(Associate associate1: associateList){
			System.out.println(associate1);
		}
		Collections.sort(associateList, new AssociateComparator());
		for(Associate associate1: associateList){
			System.out.println(associate1);
		}		
	}
}
