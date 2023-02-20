To Run Fitnium execute runAll.bat ( on Windows ) or runAll.sh ( OSX and Linux )

Once all 3 applications have been spawned and are running you can access the documentation at 

	http://localhost

Note: Sometimes the Fitnesse server takes a little time to start and the browsers starts before it. If you get an "Unable to connect" error in your browser just wait a few seconds and click refresh.

From there you will see a link to the "Fitnium User Guide"

The biggest change in terms of execution iin this release is the introduction of Jetty to host the test website, rather than having to go over the internet to http://www.magneticreason.com, which not only provides unneccassary traffic to my website,  but also means you have to be connected to the internet to run the tests.

The biggest functional change in this release is the introduction for the support of SLiM Script Table as well as the Fitnesse DoFixture. Both are support and the same Fitnium code can be used with them,

The documentation contains a common shared thread for basic topics and then splits for Fitnesse and SLiM related 