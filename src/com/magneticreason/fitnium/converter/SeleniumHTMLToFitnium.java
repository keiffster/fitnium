package com.magneticreason.fitnium.converter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.magneticreason.fitnium.converter.fit.FitWriter;
import com.magneticreason.fitnium.converter.slim.SlimWriter;
/*
Copyright 2008-9 Keith Sterling

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

public class SeleniumHTMLToFitnium {

    /**
     * @param args
     */
    public static void main(String[] args) {

    	try {
	        System.out.println("Warning: This converter is beta quality... be warned\n");
	
	        if (args.length < 3) {
	            System.out.println("Usage java -cp ./lib/** com.magneticreason.fitnium.converter.SeleniumHTMLToFitnium -s selenese_file -f fitnium_file -t fit|slim -h localhost -p port -b browser -u url ");
	            return;
	        }
	
	        CommandLine cmdLine = SeleniumHTMLToFitnium.parseCommandLine(args);
	        
	        String from = cmdLine.getOptionValue("s");
	        String to = cmdLine.getOptionValue("f");
	        String type = cmdLine.getOptionValue("t");

	        String host = cmdLine.getOptionValue("h", "localhost");
	        String port = cmdLine.getOptionValue("p", "4444" );
	        String browser = cmdLine.getOptionValue("b", "*firefox" );
	        String url = cmdLine.getOptionValue("u");

	        IWriter writer = null;
	        if ( type.equalsIgnoreCase ( "slim") ) {
	            writer = new SlimWriter ();
	            System.out.println("Framework : SLiM" );
	        }  else if (type.equalsIgnoreCase("fit")) {
	            writer = new FitWriter ();
	            System.out.println("Framework : Fit" );
	        } else {
	            System.out.println("Unknown framework, use either slim or fit only ");
	            return;
	        }
	
	        boolean verbose = false;
	        if (args.length == 4) {
	            verbose = Boolean.parseBoolean(args[2]);
	        }
	
	        SeleneseParser parser = new SeleneseParser ( writer, verbose );
	        parser.parse ( from, to, host, port, browser, url );
	
	    	System.out.println ( "Done" );
    	} catch ( Exception e ) {
    		e.printStackTrace();
    	}
    }

    protected static CommandLine parseCommandLine ( String[] args ) throws ParseException {
    	// create the command line parser
    	CommandLineParser parser = new PosixParser();

    	// create the Options
    	Options options = new Options();
    	options.addOption( "s", "selenese", true, "Name of Selenese file to read" );
    	options.addOption( "f", "fixture", true, "Name of Fitnesse fixture to write" );
    	options.addOption( "t", "type", true, "fit|slim" );
    	options.addOption( "h", "host", true, "Host Selenium server is running on ( default localhost )" );
    	options.addOption( "p", "port", true, "Port Selenium server is running on ( default 44444 )" );
    	options.addOption( "b", "browser", true, "Brower to use ( default *firefox )" );
    	options.addOption( "u", "url", true, "URL to start at" );

	    return parser.parse( options, args );

    }
}
