package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.homework.HistoryListener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
             Секунда должна определяьться во время выполнения.
             Тест - важная часть задания
             Обязательно посмотрите пример к паттерну Мементо!
       4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
          Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
          Для него уже есть тест, убедитесь, что тест проходит
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
        /*List<Processor> processors = new ArrayList<>();
        processors.add(new ProcessorEvenSecondsPhobia(LocalDateTime::now));
        processors.add(new ProcessorSwapF11F12());

        var complexProcessor = new ComplexProcessor(processors, (ex) -> System.out.println(ex.getMessage()));
        var listenerHistory = new HistoryListener();
        complexProcessor.addListener(listenerHistory);

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"ofm01", "ofm02", "ofm03");
        ObjectForMessage ofm = new ObjectForMessage();
        ofm.setData(list);*/

        // Создаем сообщение и запускаем его обработку
        /*var message = new Message.Builder(171L)
            .field11("F_11")
            .field12("F_12")
            .field13(ofm)
            .build();

        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);*/

        // Изменяем сообщение и снова обрабатываем на том же complexProcessor'е
        /*list = new ArrayList<>();
        Collections.addAll(list,"ofm_101", "ofm_102", "ofm_103");
        ofm = new ObjectForMessage();
        ofm.setData(list);
        message = message.toBuilder().field13(ofm).build();
        result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        complexProcessor.removeListener(listenerHistory);*/

        // Печатаем историю изменений. Новый ObjectForMessage не влияет на первые Message в listenerHistory
        //System.out.println("\nHistory of changes:");
        //listenerHistory.printHistory();
    }
}
