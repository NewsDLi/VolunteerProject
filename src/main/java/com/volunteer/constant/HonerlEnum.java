package com.volunteer.constant;

public enum HonerlEnum {
	
	LYZR("lyzr", "乐于助人"), 
	CZYH("czyh", "持之以恒"), 
	ADTS("adts", "爱的天使"), 
	ADFX("adfx", "爱的奉献"), 
	RAZX("razx", "爱的奉献"), 
	SHTS("shts", "守护天使");
	
	private String key;
	
	private String value;

	private HonerlEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getValue(String key){
		HonerlEnum[] businessModeEnums = values();  
        for (HonerlEnum businessModeEnum : businessModeEnums) {  
            if (businessModeEnum.key.equals(key)) {  
                return businessModeEnum.value;  
            }  
        }
        return null;
	}
	
}
