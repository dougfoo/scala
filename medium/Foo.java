public class Foo {

    public static void main (String args[]) {

        List list = new ArrayList();
        list.append(new Pair<String,Integer>("One",1))
        list.append(new Pair<String,Integer>("Two",2))
        list.append(new Pair<String,Integer>("Three",3))
        list.append(new Pair<String,Integer>("Four",4))

        // java style
        List res = new List();
        for (Pair<String, Integer> item: list) {
            if (item.getKey().equals("Three"))
                res.append(item);
        }

        // psuedo scala style
        List res = list.filter(_.getKey().equals("Three"));

        // java with java8 streams
        List res = list.stream().filter(c -> c.getKey().equals("Three")).collect(Collectors.toList());

        System.out.println(list);

        // functional methods


    }
}