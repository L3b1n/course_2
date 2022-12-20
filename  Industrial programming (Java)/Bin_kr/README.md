# Контрольная работа по промышленному программированию
В текстовом файле test1.txt находятся данные о студентах : фамилия, имя, средний бал(с буквой Е, е -это запись в экпоненциальном виде), идентификационный номер, имя файла.  
Каждая запись о студенте - в отдельной строке. Разделители : пробел, подчеркивание, точка с запятой: _;  
Разделитель "точку" в вещественных числах , для корректного чтения - можно в файле заменить на "запятую"!
  
Разработать классы (Student,  ClassBD),  разработать итерфейс InterfBD с одним методом и реализовать метод в классе.  
Класс Student - содержит информацию об одном студенте,  
класс ClassBD - содержит стандартные коллекции (List и MAP, для МАР ключ- идентификационный номер).

## В классах должны быть методы:
1. Перегруженный метод toString в классе Student
2. Перегруженный метод сравнения compare (или compareTo) для сортировки в классе Student
3. Метод чтения данных из текстового файла в List
4. Метод чтения данных из текстового файла в Map (для МАР ключ- идентификационный номер).
5. Метод удаляющий записи с некорректными данными: (фамилия с цифрами) и записывающий  в результирующий текстовый файл rezdel.txt
6. Метод - поиск студентов: с помощью регулярного выражения найти у которых файл с фото заканчивается расширением : bmp или gif или jpg и записывающий в результирующий текстовый файл rezreg.txt
7. Метод cортирующий данные класса LIST по полю имя И среднему баллу (если одинаковвые фамилии, то сортирутся по ср. баллу),
использовать Сomparator и записывающий в результирующий текстовый файл rezsort.txt
8. Запись List в JSON-формат и записывающий в результирующий текстовый файл rezjson.txt