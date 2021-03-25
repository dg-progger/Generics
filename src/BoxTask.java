public class BoxTask {

    public static void main(String[] args) {
        Box<Apple> srcBox = new Box<>();
        srcBox.putElement(new Apple());
        Box<Apple> destBox = new Box<>();
        BoxUtil.copyFreshFruitFromBoxToBox(srcBox, destBox);
        System.out.println(destBox.getElement());
    }

    static class Fruit {
        public boolean fresh = true;

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public boolean getFresh(boolean fresh) {
            return this.fresh;
        }
    }

    static class Apple extends Fruit { }

    static class Box<T> {
        T element;

        public T getElement() {
            return element;
        }

        public void putElement(T element) {
            this.element = element;
        }
    }

    public static class BoxUtil {

        // скопировать содержимое из одной коробки в другую
        //Box(dest) в которую будем копировать может быть типизирована любым родителем объекта содержащимся в Box(src)
        public static <T> void copyFromBoxToBox(Box<T> src, Box<? super T> dest) {
            dest.putElement(src.getElement());
        }

        // скопировать содержимое из Box(src) которая может быть типизирована только классом Fruit и его наследниками,
        // при условии, что содержащийся фрукт свежий (fresh == true).
        //Box(dest) в которую будем копировать может быть типизирована любым родителем объекта содержащимся в Box(src)
        public static <T extends Fruit> void copyFreshFruitFromBoxToBox(Box<T> src, Box<? super T> dest) {
            if (src.getElement().fresh) {
                copyFromBoxToBox(src, dest);
            }
        }

        //вывести в консоль (toString()) объект второй коробки
        public static <T> void printElementFromTwoBoxes(Box<Box<T>> box) {
            System.out.println(box.getElement().getElement().toString());
        }

        //вывести в консоль (toString()) объект последней коробки box Box, //
        // которая содержит в себе любое кол-во вложенных Box, в последней из которых //
        // может быть любой объект.
        //
        public static <T> void printElementFromBoxes(Box<T> box) {
            T boxContent = box.getElement();
            if (boxContent instanceof Box) {
                printElementFromBoxes((Box<T>) boxContent);
            } else {
                System.out.println(boxContent.toString());
            }
        }
    }


}
