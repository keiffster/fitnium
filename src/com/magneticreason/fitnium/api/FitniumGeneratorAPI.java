package com.magneticreason.fitnium.api;

import com.magneticreason.fitnium.FitniumFixture;
import java.util.HashMap;

import com.magneticreason.fitnium.generator.FitniumUniqueValueGenerator;

public class FitniumGeneratorAPI {
	
	   /****************************************************************************
     * Generators
     ****************************************************************************/
    
    private static HashMap<String, FitniumUniqueValueGenerator> generators = new HashMap<String, FitniumUniqueValueGenerator> ();

    /**
     * Loads a Value Generator and assigns it a name
     * @param classpath Java classpath of the Value generator, sorry .NET boys
     * @param name Name of assign to the generator
     * @throws Exception if the generator is not loaded
     * 
     * <br/><br/>
     * | load unique value generator | classpath | into | name | 
     */
    public final static void loadUniqueValueGeneratorInto ( String classpath, String name ) throws Exception {
    	
    	if ( classpath.endsWith("?"))
    		classpath = classpath.substring(0, classpath.length()-1 );
    	else if ( classpath.endsWith("[?]"))
       		classpath = classpath.substring(0, classpath.length()-3 );
    	
		FitniumUniqueValueGenerator generator = (FitniumUniqueValueGenerator) Class.forName( classpath ).newInstance();
		
		generators.put( name, generator );
    }
    
    /**
     * Loads a Value Generator and assigns it a name and initialises it with custom data
     * @param classpath Java classpath of the Value generator, sorry .NET boys
     * @param name Name of assign to the generator
     * @param init Data to initialise it, this is specific you the generator
     * @throws Exception if the generator is not loaded
     * 
     * <br/><br/>
     * | load unique value generator | classpath | into | name | and initialise with | data |
     */
    public final static void loadUniqueValueGeneratorIntoAndInitialiseWith ( String classpath, String name, String init ) throws Exception {
    	
    	if ( classpath.endsWith("?"))
    		classpath = classpath.substring(0, classpath.length()-1 );
    	else if ( classpath.endsWith("[?]"))
       		classpath = classpath.substring(0, classpath.length()-3 );
    	
		FitniumUniqueValueGenerator generator = (FitniumUniqueValueGenerator) Class.forName( classpath ).newInstance();
		
		generator.initialise( init );
		
		generators.put( name, generator );
    }

    /**
     * Loads a Value Generator and assigns it a name
     * @param classpath Java classpath of the Value generator, sorry .NET boys
     * @param name Name of assign to the generator
     * @param value Starting value to assign to generator
     * @throws Exception if the generator is not loaded
     * 
     * <br/><br/>
     * | load unique value generator | classpath | into | name | with starting value | value |
     */
    public final static void loadUniqueValueGeneratorIntoWithStartingValue ( String classpath, String name, String value ) throws Exception {
    	
    	if ( classpath.endsWith("?"))
    		classpath = classpath.substring(0, classpath.length()-1 );
    	else if ( classpath.endsWith("[?]"))
       		classpath = classpath.substring(0, classpath.length()-3 );
   	
		FitniumUniqueValueGenerator generator = (FitniumUniqueValueGenerator) Class.forName( classpath ).newInstance();

		generator.reset ( value );
		
		generators.put( name, generator );
    }

    /**
     * Store the next value from a generator into a fitnium variable for later use
     * @param genName Name of generator to get next value from 
     * @param name Name of fitnium variable to store value in
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | store next unique value from | genName | in | name |
     */
	public final static void storeNextUniqueValueFromIn ( FitniumFixture fitnium, String genName, String name ) throws Exception  {
    	FitniumVariableAPI.setFitniumVariableCalledWithValue( fitnium, name,
    				FitniumGeneratorAPI.nextUniqueValueFrom( genName ) );
	}

    /**
     * Store the next value from a generator into a fitnium variable for later use
     * @param genName Name of generator to get next value from 
     * @param params Custom paramters to pass to next value function
     * @param name Name of fitnium variable to store value in
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | store next unique value from | genName | with params | params | in | name |
     */
	public final static void storeNextUniqueValueFromWithParamsIn ( FitniumFixture fitnium, String genName, String params, String name ) throws Exception  {
		FitniumVariableAPI.setFitniumVariableCalledWithValue( fitnium, name,
					FitniumGeneratorAPI.nextUniqueValueFromWithParams( genName, params ) );
	}

	/**
	 * Get the next unique value
	 * @param name Name of the generator to get value from
	 * @return Next unique value
	 * @throws Exception if something goes wrong
	 * 
	 * <br/><br/>
	 * | next unique value from | name |
	 */
	public final static String nextUniqueValueFrom ( String name ) throws Exception {
    	FitniumUniqueValueGenerator generator = generators.get( name );
    	if ( null == generator )
    		throw new Exception ( "Unknown unique generator : " + name );
    	return generator.getNextValue();
    }
    
	/**
	 * Get the next unique value by passing in custom data
	 * @param name Name of the generator to get value from
	 * @param params Custom data to pass to function
	 * @return Next unique value
	 * @throws Exception if something goes wrong
	 * 
	 * <br/><br/>
	 * | next unique value from | name | with params | params |
	 */
    public final static String nextUniqueValueFromWithParams ( String name, String params ) throws Exception {
    	FitniumUniqueValueGenerator generator = generators.get( name );
    	if ( null == generator )
    		throw new Exception ( "Unknown unique generator : " + name );
    	return generator.getNextValue( params );
    }
    
    /**
     * Reset the generator ( where application )
     * @param name Name of generator to reset
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | reset unique value generator | name |
     */
    public final static void resetUniqueValuedGenerator ( String name ) throws Exception  {
    	FitniumUniqueValueGenerator generator = generators.get( name );
    	if ( null == generator )
    		throw new Exception ( "Unknown unique generator : " + name );
    	generator.reset();
    }

    /**
     * Reset the generator ( where application ) to a given value
     * @param name Name of generator to reset
     * @param value Value to reset data to
     * @throws Exception if something goes wrong
     * 
     * <br/><br/>
     * | reset unique value generator | name | back to | value |
     */
    public final static void resetUniqueValuedGeneratorBackTo ( String name, String value ) throws Exception {
    	FitniumUniqueValueGenerator generator = generators.get( name );
    	if ( null == generator )
    		throw new Exception ( "Unknown unique generator : " + name );
    	generator.reset( value );
    }


}
