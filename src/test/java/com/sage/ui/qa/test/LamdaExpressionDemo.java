package com.sage.ui.qa.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.testng.annotations.Test;

public class LamdaExpressionDemo {
	
	
	//@Test
	public void testlamdaexpression(){
		
	Comparator<String> compString = (o1,o2) -> o1.compareTo(o2);
	
	System.out.println(compString.compare("Hi", "Test"));
}
	
	
	//@Test
	public void testlamdaInterface(){
		MyInterface myinterface = (text1, text2) -> {return text1.concat(text2); };
		
		String returnVal = myinterface.apply("Sample Text 1","Sample Text 2");
		
		System.out.println(returnVal);
		//myinterface.
	}
	
	
	//streams -java
	
	//@Test
	public void testJavaIntStreams(){
		IntStream.range(1, 10).forEach(System.out::print);
		System.out.println("....END..");
		IntStream.range(1, 9).skip(4).forEach(x -> System.out.print(x));
		System.out.println("....THE END..");
	}
	
//	@Test
	public void testArrayListwithStream(){
		List<String> familyFriends = Arrays.asList("Ganesh", "Santosh", "Soujanya", "Saunak", "Ravi", "Santi");
		familyFriends.stream().map(String::toLowerCase).filter(x -> x.startsWith("s"))
				.forEach(x -> System.out.println(x));
		//familyFriend
			
	}
	//@Test
	public void testStreamCollectors() throws IOException{
		List<String> friendsList =Files.lines(Paths.get("FriendsList.txt")).filter(x -> x.toUpperCase().contains("K")).collect(Collectors.toList());
		friendsList.forEach(x -> System.out.println(x));
		
	}
	
	@Test
	public void testSteamwithHashMap() throws IOException{
		Stream<String> empList = Files.lines(Paths.get("EmployeeList.txt"));
		
		Map<String,String> mapObject = new HashMap<String,String>();
		
		mapObject = empList.map(x -> x.split(" ")).collect(Collectors.toMap(x -> x[0], x -> x[1]));
		
		empList.close();
		
	//	forEach(mapObject) {System.out.println(mapObject);}
		for(String key : mapObject.keySet())
		{
			System.out.println("Key->" +key +"  Value-->"+mapObject.get(key));
		}
		
	}
	
}	
