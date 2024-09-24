# Proje Adı : 
AppFellasFlightApi

# Başlarken:
Bu bölüm, projeyi yerel geliştirme ortamında nasıl çalıştıracağınızı açıklar.

# Ön Koşullar: 
Projeyi çalıştırmak için gerekli olan yazılımların listesi:
- JAVA JDK 17;
- MAVEN
- MONGODB
- MONGODB COMPASS (Veri tabanı içerisindeki bilgileri görmek isterseniz kurabilirsiniz.)

# Kurulum (Linux) :
- MongoDB:
  https://www.mongodb.com/try/download/community adresine gidiniz.
  ![mongo-community](https://github.com/user-attachments/assets/c5bc20b6-9a0f-4053-80a2-cf182783305c)
  Linux sisteminize göre seçimi yaptıktan sonrasında download butonuna basarak dosyayı indiriniz.
  Terminalinizi açınız (Ctrl + Alt + T)
  cd Downloads/ komutu ile indirilenler dosyasına giriniz.
  sudo dpkg -i mongodb-org-server_6.0.5_amd64.deb komutu ile yükleme işleminizi yapınız.
  sudo systemctl status mongod komutu ile mongodb çalışma durumunuzu kontrol ediniz.
  active(running) yazısı yok ise
  sudo systemctl start mongod komutu ile mongodb sunucunuzu başlatınız.
  tekrardan sudo systemctl status mongod komutu ile sunucu durumunu kontrol ediniz. active(running) ifadesini görüyorsanız mongo sunucunuz çalışır duruma geçmiştir.
  https://www.mongodb.com/try/download/shell adresine gidiniz.
  ![Screenshot from 2024-09-24 14-11-51](https://github.com/user-attachments/assets/f4057d93-8684-4dde-922f-8f145d4d55e2)

  MongoDB Shell Download kısmından indirmeyi başlatınız.
  Yeni bir terminalinizi açınız.
  sudo dpkg -i mongodb-mongosh_1.8.2_amd64.deb komutunu çalıştırınız.
  mongosh komutu ile shell durumunu kontrol ediniz.
  https://www.mongodb.com/try/download/compass adresine gidiniz.
  ![Screenshot from 2024-09-24 14-11-59](https://github.com/user-attachments/assets/7fe633ca-7da8-4e8a-a78d-f9152ad20161)

  MongoDB Compass Download (GUI) kısmından indirmeyi başlatınız.
  sudo dpkg -i mongodb-compass_1.36.4_amd64.deb komutunu çalıştırınız.
  Tebrikler. MongoDb sunucunuz artık çalışıyor.
  
# Uygulamayı Çalıştırma :
Uygulamayı çalıştırmak için aşağıdaki adımları izleyiniz :

### Application.yml içerisindeki database bağlantısını yapınız!!!

Proje dizinine gidin.
mvn spring-boot:run komutunu çalıştırın.
Postman üzerinden veya front end projesi üzerinden uygulamaya istek atabilirsiniz.
Uygulama içerisindeki gerekli ayarlamalar kullanıcılar, uçuşlar, havaalanları ve hava yolu şirketleri AppConfig.java dosyası içerisinde hazır olarak gelmektedir.
AppConfig.java içerisinde bulunan fetchFlights metodu case içerisinde verilen API'ye istekte bulunur ve gelen uçuşları veritabanına kaydeder.

# Sistem Tarafından Hazır Olarak Eklenen Kullanıcılar :
ROLE: ADMIN

Email: ahmetcan.yalcinkaya55@gmail.com
Şifre: Ahmet123.

ROLE: ADMIN

Email admin@appfellas.com
Şifre: APPFELLAS

ROLE: USER

Email: user@appfellas.com
Şifre: APPFELLASUSER


https://github.com/Ahmetyalcinkya/AppFellasFlightApiFE adresinde bulunan FrontEnd projesinden veya Postman üzerinden uygulamaya istek gönderebilirsiniz.










