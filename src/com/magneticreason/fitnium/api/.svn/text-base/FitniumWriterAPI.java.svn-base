package com.magneticreason.fitnium.api;

import java.util.HashMap;

import com.magneticreason.fitnium.writer.FitniumWriter;

public class FitniumWriterAPI {
	
    /****************************************************************************
     * Writers
     ****************************************************************************/
    
    private static HashMap<String, FitniumWriter> writers = new HashMap<String, FitniumWriter> ();
    
    /**
     * Load a write into the system and assign it a name we can use
     * @param classpath Classpath of write to load
     * @param name Name to assign it
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | load writer | classpath | into | name 
     */
    public final static void loadWriterInto ( String classpath, String name ) throws Exception {
    	
    	if ( classpath.endsWith("?"))
    		classpath = classpath.substring(0, classpath.length()-1 );
    	else if ( classpath.endsWith("[?]"))
       		classpath = classpath.substring(0, classpath.length()-3 );
    	
    	FitniumWriter writer = (FitniumWriter) Class.forName( classpath ).newInstance();
		
		writers.put( name, writer );
    }
    
    /**
     * Load a write into the system and assign it a name we can use
     * @param classpath Classpath of write to load
     * @param name Name to assign it
     * @param params Additional data to pass to the writer
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | load writer | classpath | into | name | with params | params |
     */
    public final static void loadWriterIntoWithParams ( String classpath, String name, String params ) throws Exception {
    	
    	if ( classpath.endsWith("?"))
    		classpath = classpath.substring(0, classpath.length()-1 );
    	else if ( classpath.endsWith("[?]"))
       		classpath = classpath.substring(0, classpath.length()-3 );
    	
    	FitniumWriter writer = (FitniumWriter) Class.forName( classpath ).newInstance();
		
    	writer.initialise( params );
    	
		writers.put( name, writer );
    }

    /**
     * Store a value in the write for saving later
     * @param value Value to save
     * @param name Name of writer to save it in
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | store value | value | in writer | name |
     */
    public final static void storeValueInWriter ( String value, String name ) throws Exception {
    	FitniumWriter writer = writers.get( name );
    	if ( null == writers )
    		throw new Exception ( "Unknown writer : " + name );
    	writer.saveValue(value);
    }

    /**
     * Store a value in the write for saving later
     * @param value Value to save
     * @param name Name of writer to save it in
     * @param params Additional data to pass to store
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | store value | value | with params | params | in writer | name |
     */
    public final static void storeValueWithParamsInWriter ( String value, String params, String name ) throws Exception {
    	FitniumWriter writer = writers.get( name );
    	if ( null == writer )
    		throw new Exception ( "Unknown writer : " + name );
    	writer.saveValue(value, params);
    }
 
    /** 
     * Flush the data from the writer to the storage systm
     * @param name Name of write to flush
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | flush writer | name |
     */
    public final static void flushWriter ( String name ) throws Exception {
    	FitniumWriter writer = writers.get( name );
    	if ( null == writer )
    		throw new Exception ( "Unknown writer : " + name );
    	writer.flush();
    }
    

}
