package commonUtilities;

/*
 * This is a helper class for RetryAnalyzer class and it is simply implementing IAnnotationTransformer 
 * 
 * In order to use this we need to call the same in the testng.xml with below things.
 *   
	<suite name="<Name of the suite>" verbose="1" >
	 <listeners>
	        <listener class-name="<packageName>.TransformAnalyzer"/>
	  </listeners>
	  <test name="<Name of the test>">
 * 
 * @author Abhishek Shandilya
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class TransformAnalyzer implements IAnnotationTransformer {
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}