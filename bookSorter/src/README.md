Book isminde bir sınıf tasarlandı. Book sınıfı kitap ismi, sayfa sayısı, yazarın ismi, yayın tarihi değişkenlerinden oluşturuldu.
Bu sınıfta Comparable interface'den kalıtım alınıp "compareTo" metodunu override edilerek kitabın ismi alfabetik olacak şekilde sıralama işlemi yapıldı.
1. Set tipindeki yapı: Sınıftan 5 tane nesne oluşturuldu. Oluşturulan bu nesneler TreeSet yapısında tutuldu. Bu TreeSetteki veriler alfabetik olarak sıralandı.
   
2. Set tipinde yapı: Book sınıfından oluşturulan nesneler yeni bir TreeSet içine eklendi.Bu TreeSetteki veriler sayfa numaralarına göre sıralandı.
Sayfa numaralarına göre sıralama yapılırken 2 farklı yöntem gösterildi.
İlki: Treesetin kurucu metoduna Comparator sınıfını göndermek ve anonim class içinde Comparator interfacesinde "compare" metodunun override edilmesi.
İkincisi: OrderPageNumberComparator isimli yeni bir class oluşturup bu classı Comparator'a implement edip içerisinde "compare" metodunu override etmek.





