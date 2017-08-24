package com.zc.support.doman;


public interface ZCImplReqParamGetter {
	

	/**
	 * 根据key获取req中的参数值（int类型），若不存在则返回0，若多个则进行求和运算。
	 * 
	 */
	public int getReqParamInt(String key);

	/**
	 * 根据key获取req中的参数数组（int类型），若不存在则返回[0]。
	 * 
	 */
	public int[] getReqParamInts(String key);

	/**
	 * 根据key获取req中的参数值（double类型），若不存在则返回0，若多个则进行求和运算。
	 * 
	 */
	public double getReqParamDouble(String key);

	/**
	 * 根据key获取req中的参数数组（double类型），若不存在则返回[0]。
	 * 
	 */
	public double[] getReqParamDoubles(String key);

	/**
	 * 根据key获取req中的参数值（String类型），若不存在则返回""，若多个则进行拼接处理。
	 * 
	 */
	public String getReqParamString(String key);

	/**
	 * 根据key获取req中的参数数组（String类型），若不存在则返回[""]。
	 * 
	 */
	public String[] getReqParamStrings(String key);

	/**
	 * 根据key获取req中的参数值（int类型），若不存在则返回0，若多个则进行求和运算。
	 * 
	 * 支持打印log
	 * 
	 */
	public int getReqParamIntWithLog(String key);

	/**
	 * 根据key获取req中的参数数组（int类型），若不存在则返回[0]。
	 * 
	 * 支持打印log
	 * 
	 */
	public int[] getReqParamIntsWithLog(String key);

	/**
	 * 根据key获取req中的参数值（double类型），若不存在则返回0，若多个则进行求和运算。
	 * 
	 * 支持打印log
	 * 
	 */
	public double getReqParamDoubleWithLog(String key);

	/**
	 * 根据key获取req中的参数数组（double类型），若不存在则返回[0]。
	 * 
	 * 支持打印log
	 * 
	 */
	public double[] getReqParamDoublesWithLog(String key);

	/**
	 * 根据key获取req中的参数值（String类型），若不存在则返回""，若多个则进行拼接处理。
	 * 
	 * 支持打印log
	 * 
	 */
	public String getReqParamStringWithLog(String key);

	/**
	 * 根据key获取req中的参数数组（String类型），若不存在则返回[""]。
	 * 
	 * 支持打印log
	 * 
	 */
	public String[] getReqParamStringsWithLog(String key);

}
