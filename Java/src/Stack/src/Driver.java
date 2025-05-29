package Stack.src;

public class Driver {

    public class Person implements Comparable<Person> {

        String name;
        int age;
        double weight;

        public Person(String nameIn, int ageIn, double weightIn) {
            name = nameIn;
            age = ageIn;
            weight = weightIn;
        }


        @Override
        public int compareTo(Person o) {
            if (this.age < o.age) {
                return -1;
            } else if (this.age > o.age) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "[name: " + this.name + ", age: " + age + ", weight: " + weight + "]";
        }

    }

    public static void main(String[] args) {
        Driver d = new Driver();
        
        LL_Stack<Integer> stack1 = new LL_Stack<>();
        for (int index = 0; index < 5; index ++) {
            stack1.push(index);
        }
        System.out.println(stack1.toString() + "\n");

        LL_Stack<Person> people = new LL_Stack<>();
        
        for (int index = 0; index < 5; index ++) {
            Person p = d.new Person("john" + index, (30 + 2*index / 3), (140.0 + 5*index));
            people.push(p);
        }
        people.pop();
        System.out.println(people.toString());
    }
}
