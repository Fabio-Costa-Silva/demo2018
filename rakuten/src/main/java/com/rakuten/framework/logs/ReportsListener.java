package com.rakuten.framework.logs;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by FabioCosta
 */
    public class ReportsListener implements ITestListener {
        @Override
        public void onTestStart(ITestResult iTestResult) {
            System.out.println("Starting test: " + iTestResult.getMethod().getDescription());
        }

        @Override
        public void onTestSuccess(ITestResult iTestResult) {
            System.out.println("Test Passed  : " + iTestResult.getMethod().getDescription() + " time taken: " + ((iTestResult.getEndMillis() - iTestResult.getStartMillis()) / 1000) + 's');
        }

        @Override
        public void onTestFailure(ITestResult iTestResult) {
            System.out.println("Test Failed  : " + iTestResult.getMethod().getDescription());
        }

        @Override
        public void onTestSkipped(ITestResult iTestResult) {
            System.out.println("Test Skipped : " + iTestResult.getMethod().getDescription());
        }

        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
            System.out.println("Test Failed  : " + iTestResult.getMethod().getDescription());
        }

        @Override
        public void onStart(ITestContext iTestContext) {
            System.out.println("Starting Test Suite: " + iTestContext.getName());
        }

        @Override
        public void onFinish(ITestContext iTestContext) {
            System.out.println("Finished Test Suite: " + iTestContext.getName());
        }
    }