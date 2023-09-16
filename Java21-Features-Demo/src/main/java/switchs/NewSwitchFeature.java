//package switchs;
//
//import switchs.sub.Animal;
//import switchs.sub.Bird;
//import switchs.sub.Cat;
//import switchs.sub.Dog;
//
//public class NewSwitchFeature {
//
//    public static void main(String[] args) {
//        // assume we do not know which animal it is
//        Animal animal = new Dog();
//
//        switch (animal) {
//            case Dog dog -> dog.bark();
//            case Cat cat -> cat.meow();
//            case Bird bird -> bird.chirp();
//            default -> throw new IllegalStateException("Unexpected value: " + animal);
//        }
//
//    }
//}
