package com.addons.mobilegesture2.ios;

import com.testsigma.sdk.ApplicationType;
import com.testsigma.sdk.IOSAction;
import com.testsigma.sdk.Result;
import com.testsigma.sdk.annotation.Action;
import com.testsigma.sdk.annotation.Element;
import com.testsigma.sdk.annotation.TestData;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.Data;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;


import java.time.Duration;

@Data
@Action(actionText = "Perform Pinch Gesture in element with scale scale", applicationType = ApplicationType.IOS)
public class PinchGesture extends IOSAction {

    @TestData(reference = "scale")
    private com.testsigma.sdk.TestData testData;
    @Element(reference = "element")
    private com.testsigma.sdk.Element uiIdentifier;


    @Override
    protected com.testsigma.sdk.Result execute() throws NoSuchElementException {
        //Your Awesome code starts here
        com.testsigma.sdk.Result result = com.testsigma.sdk.Result.SUCCESS;
        logger.info("Initiating execution");
        logger.debug("ui-identifier: "+ this.uiIdentifier.getValue() +" by:"+ this.uiIdentifier.getBy() + ", test-data: ");
        IOSDriver iosDriver = (IOSDriver)this.driver;
        WebElement element = uiIdentifier.getElement();//Alternate way iosDriver.findElement(uiIdentifier.getBy())
        if(element.isDisplayed()){
            logger.info("Element is displayed, entering data");
            setSuccessMessage(String.format("Element Visible Performing Actions", uiIdentifier.getBy(), uiIdentifier.getValue()));
        }else{
            result = com.testsigma.sdk.Result.FAILED;
            logger.warn("Element with locator %s is not visible ::"+uiIdentifier.getBy()+"::"+uiIdentifier.getValue());
            setErrorMessage(String.format("Element with locator %s is not visible",uiIdentifier.getBy()));
        }

        final Dimension size = element.getSize();
        final Point upperLeft = element.getLocation();
        final Point center = new Point(upperLeft.getX() + size.getWidth() / 2, upperLeft.getY() + size.getHeight() / 2);
        final int yOffset = center.getY() - upperLeft.getY();
        try {
            final Point endPoint = new Point(center.getX(), (int)(center.getY() + yOffset * 0.4));
            final TouchAction touch0 = new TouchAction((PerformsTouchActions)iosDriver).press(PointOption.point(center.getX(), (int)(center.getY() + yOffset * 0.35))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(450L))).moveTo(PointOption.point(endPoint)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(0L)));
            final TouchAction touch2 = new TouchAction((PerformsTouchActions)iosDriver).press(PointOption.point(center.getX(), (int)(center.getY() + yOffset * 0.45))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(450L))).moveTo(PointOption.point(endPoint));
            final MultiTouchAction multiTouchAction = new MultiTouchAction((PerformsTouchActions)iosDriver);
            for (int scaleIndex = 0; scaleIndex < Integer.parseInt(testData.getValue().toString()); ++scaleIndex) {
                multiTouchAction.add(touch0).add(touch2).perform();
            }
            setSuccessMessage("Pinch Gesture Successfully Performed");

        }
        catch (Exception e) {
            result = Result.FAILED;
            logger.info(e.getMessage());
            setErrorMessage("Pinch Zoom Failed"+e.getCause().toString());
            e.printStackTrace();

        }
            return result;
    }
}
