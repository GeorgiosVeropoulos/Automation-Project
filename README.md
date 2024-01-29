<h2>Weclome to the Automation-Project.</h2>
<h3>This framework serves as a way to Test any Webpage.</h3>
<h4>The current focus of this project is to Test my webPage: 
<a href="georgeveropoulos.com">georgeveropoulos.com</a>
<p>Framework contains:</p>
<ul>
<li>TestNG: Test execution and asserts.</li>
<li>Selenide: selectors and webUI interaction.</li>
<li>ExtentReport: for test reports.</li>
<li>Testrail: API integration for updating Cases in Runs.</li>
<li>Managers: for Test Configurations using .properties file and data.</li>
</ul>

<p>Project supports parallel execution of TestCases with independent logging on the ExtentReport</p>

<h3>Quick introduction</h3>
<ul><h2>TestBase.class</h2>
    <li>Contains all the implementation of @Before and @After methods.</li>
    <li>All TestCases must extend this.</li>
</ul>
<ul><h2>Page.class</h2>
    <li>All <b>Pages</b> must extend this class.</li>
    <li>Contains basic methods to be used by most Tests</li>
</ul>
<ul><h2>Browser.class</h2>
    <li>Implements all the configuration and capabilities for the Browser to use.</li>
    <li>Currently only Chrome is added.</li>
    <li>Other browser can be added with the same logic.</li>
</ul>


<h1>Execution of TestCases/Suite</h1>
<p><b>Each TestNG configuration or CL must contain the following:</b></p>
<p>-DreportName=THE_NAME_OF_YOUR_REPORT</p>
<p>-Dproperty.file=THE.properties file to be used (current file config.properties)</p>
<p>If you are running a suite you can select the groups to run by using -DgroupsToRun=YOUR_GROUP_NAME</p>












