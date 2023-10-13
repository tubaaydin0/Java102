public class MyList <T> { // Generic bir sınıf
    private int defaultSize=0; // dizinin varsayılan boyutu
    private int capacity=0; // Dizinin kapasitesi
    private int size; // Dizideki eleman sayısı


    T[] list;
    T[] tempList;
    public MyList(){// 1. Constructor
       this.capacity=10; // Generic liste tanımlarken T[] list=(T[]] new Object[size]; şeklinde tanımlanır.
       this.list=(T[]) new Object[this.capacity];

    }
    public MyList(int capacity){// 2. Constructor
        this.capacity=capacity;
        this.list=(T[]) new Object[this.capacity];
    }
    public void add(T data){
        if (this.size <this.capacity-1){
            this.list[size]=data;
            //System.out.println("veri eklendi.");
            setSize(getSize()+1);
        }
        else{

            this.setCapacity(getCapacity()*2);
            tempList=(T[])new Object[this.capacity];
            for (int i=0; i<this.list.length;i++){ // Eski dizinin kapasitesi kurucu metotta 10 olduğu için her çalıştığında 10 olarak yinelenecek. Bu yüzden geçiçi bir liste oluşturup içindeki elemanları oraya kopyalıyoruz.
                tempList[i]=list[i];
            }
            //tempList in capasity :20 , list in capacity:10
            this.list=(T[]) new Object[this.capacity]; // yeni bir list nesnesi oluşturduk. boyutu 20 ama içi boş. Buna da tempListi kopyaladık. Böylece list yenilenmiş oldu
            for (int i=0; i<this.tempList.length;i++){
                list[i]=tempList[i];
            }
            //tempList in capasity :20 , list in capacity:200
            this.list[size]=data; // yenilenen list e veri ekliyoruz
            setSize(getSize()+1); // veri eklendikçe içindeki eleman sayısı artırıyoruz.
        }
    }
    public T get(int index){
        if (index<getSize()){
            return this.list[index];
        }else{
            return null;
        }
    }
    //remove(int index): verilen indisteki veriyi silmeli ve silinen indis sonrasında ki verileri sol doğru kaydırmalı.
    // Geçersiz indis girilerse null döndürür.
    // 5 4 3 2  indis no: 0 1 2 3 , 1 i sil
    // 5 3 2
    public void remove(int index){
        //this.list[index]=null;
        if (index<getSize()){

            for(int i=index+1; i<list.length ; i++){ // aşağıda i-1 diyoruz. index numarasından başlaması için 1 arttırdık.
                list[i-1]=list[i]; // silinen indexteki verinin yerine bir sonraki indexin verisi geçiyor.

            }
            setSize(getSize()-1); // eleman sayısı bir azaltıldı
        }
    }
    //set(int index, T data) : verilen indisteki veriyi yenisi ile değiştirme işlemini yapmalıdır.
    // Geçersiz indis girilerse null döndürür.
    public T set(int index, T data){
        if (index<getSize()){
            this.list[index]=data;
            return this.list[index];

        }else{
            return null;
        }
    }
    //String toString() : Sınıfa ait dizideki elemanları listeleyen bir metot.
    public String toString(){

        System.out.print("[");
        for (T arr: this.list){
            if (arr!=null){
                System.out.print(arr+" ");
            }
        }
        System.out.println("]");
        return list.toString();
    }

    //int indexOf(T data) : Parametrede verilen nesnenin listedeki indeksini verir. Nesne listede yoksa -1 değerini verir.
    public int indexOf(T data){
        for (int i=0; i<this.list.length;i++){// Döngüyle list in tüm elemanlarına bakılır
            if(data==this.list[i]){ // data list in i. elemanına eşitse i yi döndürür yani indisini döndürür.
                return i;
            }
        }
        return -1; // Data döngüde bulunamazsa list de yok demektir. -1 döndürür.

    }
    //int lastIndexOf(T data) : Belirtilen öğenin listedeki son indeksini söyler. Nesne listede yoksa -1 değerini verir.
    public int lastIndexOf(T data){
        for (int i=this.list.length-1; i>=0 ; i--){ // datanın bulunduğu son indeksi istediği için list in elemanlarını son indisinden başlayarak geziyoruz.
            if (data==this.list[i]){ // data hangi indisteyse o son indistir
                return i;
            }
        }
        return -1;
    }
    //boolean isEmpty() : Listenin boş olup olmadığını söyler.
    public boolean isEmpty(){
        int count=0;
        for (T empty : this.list){
            if (empty!=null){
                return false;
            }
        }

        return true;
    }
    //T[] toArray() : Listedeki öğeleri, aynı sırayla bir array haline getirir.
    public T[] toArray(){
        T[] array=(T[]) new Object[this.getSize()];
        for (int i=0 ; i<this.getSize();i++){
            array[i]=this.list[i];
        }
        return array;
    }

    //clear() : Listedeki bütün öğeleri siler, boş liste haline getirir.
    public void clear(){
        for (int i=0; i<this.list.length;i++){
            this.list[i]=null; // tüm elemanları null yaptık.
        }
    }
    //MyList<T> sublist(int start,int finish) : Parametrede verilen indeks aralığına ait bir liste döner.
    public MyList<T> sublist(int start, int finish){
        MyList<T> array=new MyList<>();
        for (int i=start; i<=finish;i++){
            array.add(this.list[i]);

        }
        return array;
    }
    //boolean contains(T data) : Parametrede verilen değerin dizide olup olmadığını söyler.
    public boolean contains(T data){
        for (int i=0; i<this.list.length;i++){
            if (this.list[i]==data){
                return true;
            }
        }
        return false;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
