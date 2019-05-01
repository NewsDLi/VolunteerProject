package com.volunteer.constant;

public enum CommonEnum {
	
	LFHD("lfhd", "了凡厚道"), 
	CAQS("caqs", "慈爱期数"), 
	XDQS("xdqs", "孝道期数"), 
	GGQS("ggqs", "改过期数"), 
	CAQZ("caqz", "慈爱亲子");
	
	private String key;
	
	private String value;

	private CommonEnum(String key, String value) {
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
		CommonEnum[] businessModeEnums = values();  
        for (CommonEnum businessModeEnum : businessModeEnums) {  
            if (businessModeEnum.key.equals(key)) {  
                return businessModeEnum.value;  
            }  
        }
        return null;
	}
	
}
