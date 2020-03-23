package ru.gb.jtwo.lc.online;

import java.util.*;
/*
* 	 1. Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
	  - Найти список слов, из которых состоит текст (дубликаты не считать);
	  - Посчитать сколько раз встречается каждое слово (использовать HashMap);
	 2. Написать простой класс PhoneBook(внутри использовать HashMap):
	  - В качестве ключа использовать фамилию
	  - В каждой записи всего два поля: phone, e-mail
	  - Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов), и отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией может быть несколько записей. Итого должно получиться 3 класса Main, PhoneBook, Person.

* */

public class MainCollections {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("January", 1);
        map.put("February", 2);
        map.put("March", 3);
        map.put("April", 4);
        map.put("January", 10);
        System.out.println(map.get("November"));
        System.out.println(map);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }

    private static void treesSample() {
        TreeSet<Box> tree = new TreeSet<>();
        tree.add(new Box(4,4));
        tree.add(new Box(1,1));
        tree.add(new Box(2,2));
        tree.add(new Box(3,3));
        System.out.println(tree);
    }

    private static void setSample() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("January");
        set.add("February");
        set.add("March");
        set.add("April");
        set.add("January");
        System.out.println(set);
    }

    private static void listsExample() {
        LinkedList<Box> list = new LinkedList<>();
        Box b1 = new Box(1, 1);
        Box b2 = new Box(1, 1);
        list.add(b1);
        list.add(new Box(2, 2));
        list.add(new Box(3, 3));
        list.add(new Box(4, 4));
        System.out.println(list);

        System.out.println(list.contains(b2));
        System.out.println(b1.hashCode());
        System.out.println(b2.hashCode());
    }

    private static void listSample() {
        ArrayList<String> list = new ArrayList<>();
        list.add("January");
        list.add("February");
        list.add("March");
        System.out.println(list);

        for (String i : list) {
            System.out.println(i);
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s);
        }

        System.out.println(list.remove(list.size() - 1));
    }
}
