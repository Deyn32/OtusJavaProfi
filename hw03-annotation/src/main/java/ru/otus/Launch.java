package ru.otus;

import lombok.extern.slf4j.Slf4j;
import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;
import ru.otus.exception.SuccessTestException;
import ru.otus.model.DemoClassTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Launch {

    private static int countSuccessTests = 0;
    private static int countFailedTests = 0;

    public static void main(String[] args) {

        log.info("Запуск тестов для DemoClassTest...\n");
        try {
            launchTest(DemoClassTest.class);
        } catch (Exception ex) {
            log.error("Ошибка обработки методов класса DemoClass!");
        }
    }

    public static void launchTest(Class<?> clazz)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Method[] methods = clazz.getDeclaredMethods();
        List<List<Method>> execPlan = addTestMethods(methods);
        addBeforeAndAfterMethods(methods, execPlan);

        for (List<Method> testList : execPlan) {

            final Object testObj = clazz.getConstructor().newInstance();
            for (Method method : testList) {
                try {
                    method.invoke(testObj);
                } catch (InvocationTargetException e) {
                    Throwable ext = e.getCause();
                    if (ext instanceof SuccessTestException) {
                        log.info("Тест " + method.getName() + "': ПРОЙДЕН");
                        countSuccessTests++;
                    } else {
                        log.info("Тест " + method.getName() + "': ПРОВАЛЕН");
                        countFailedTests++;
                    }
                }
            }
        }
        print();
    }

    private static List<List<Method>> addTestMethods(Method[] methods) {
        List<List<Method>> execPlan = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                List<Method> testList = new ArrayList<>();
                testList.add(method);
                execPlan.add(testList);
            }
        }
        return execPlan;
    }

    private static void addBeforeAndAfterMethods(Method[] methods, List<List<Method>> execPlan) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                execPlan.forEach(testList -> testList.add(0, method));
            }
            if (method.isAnnotationPresent(After.class)) {
                execPlan.forEach(testList -> testList.add(method));
            }
        }
    }

    private static void print() {
        log.info(printResult());
        countSuccessTests = 0;
        countFailedTests = 0;
    }

    private static String printResult() {
        return String.format("""
                Всего было запущено тестов: %d
                Пройдено тестов: %d
                Провалено тестов: %d
                """, (countSuccessTests + countFailedTests), countSuccessTests, countFailedTests);
    }
}
