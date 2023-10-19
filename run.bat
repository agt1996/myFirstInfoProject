set projectLocation=C:\Users\akshay thadkar\eclipse-workspace-new\com.infy.in
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause