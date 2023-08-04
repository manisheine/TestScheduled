package com.vinculum.dynamic.events;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

public class PropertyInspector<T> {
	
	
	public void check(final T object) {
		//Dynamically Check the class name
		final Class<?>  clazz = object.getClass();
		//Get the declared methods
		final Field[] fields = clazz.getDeclaredFields();
		//Check null
		if(null != fields) {
			//Loop
			for(final Field field: fields) {
				//Set the Access
				field.setAccessible(true);
				//Get the Type Class
				final Class<?>  type = field.getType();
				//Get the Name
				final String name = type.getName();
				
				//Check
				if(StringUtils.equals(name, "java.lang.String")) {
					//Handle Exception
					try {
						//Get the Value
						if(null == field.get(object)) {
							//Set
							field.set(object, "");
						}
					}catch(final Exception exception) {
						//Print the stack trace
						exception.printStackTrace();
					}
					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		final PropertyInspector<Manish> object = new PropertyInspector<>();
		Manish m= new Manish();
		m.setFirstname("Manish");
		object.check(m);
		System.out.println(m.getFirstname());
		System.out.println(m.getLastname());
	}
}




class Manish{
	private String firstname;
	private String lastname;
	private int age;
	private Long phone;

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
