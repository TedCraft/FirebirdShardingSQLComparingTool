## Гайд на сборку и тестирование Apache ShardingSphere

**Официальный сайт:** [https://shardingsphere.apache.org/](https://shardingsphere.apache.org/)

### Установка необходимых зависимостей

<hr>

`Редактор кода:` Можно использовать любой понравившийся редактор кода для Java. В данном руководстве будет использована [IntelliJ IDEA](https://www.jetbrains.com/ru-ru/idea/download).

`JAVA:` Необходимо использовать версию JDK 11 или выше. Если была установлена IntelliJ IDEA, то Java устанавливается вместе с ней, иначе её можно скачать [здесь](https://adoptium.net/temurin/releases/?version=11).

Для пользователей Windows также необходимо убедиться, что была создана системная переменная JAVA_HOME. Для этого в настройках необходимо перейти по пути Система - О программе - Дополнительные параметры системы - Переменные среды. Здесь, в нижнем окошке, необходимо создать новую переменную JAVA_HOME со значением абсолютного пути к папке с Java (например: C:\Program Files\Eclipse Adoptium\jdk-11.0.18.10-hotspot). Также может быть необходимо добавить путь к папке bin в переменную Path (например: C:\Program Files\Eclipse Adoptium\jdk-11.0.18.10-hotspot\bin).

`СУБД:` Для работы проекта необходимо скачать и установить [СУБД Ред База Данных](https://reddatabase.ru/ru/downloads/rdb50/).

`Плагины IDE:` Если была выбрана не IDEA необходимо установить плагин Lombok. Также можно установить плагин ANTLR v4 для более удобной отладки SQL парсера.

### Сборка Apache ShardingSphere

<hr>

В первую очередь необходимо скачать [проект](https://github.com/red-soft-ru/shardingsphere) с github.

Далее необходимо открыть папку с проектом в терминале. Сделать это можно с помощью команды `cd "Путь до папки"`. Для пользователь Windows также можно открыть папку через проводник и заместо пути к папке (сверху, слева от поиска) прописать `cmd` и нажать enter.

Команда для сборки проекта на Linux:

```
./mvnw clean install -Prelease -T1C -DskipTests -Djacoco.skip=true -Dcheckstyle.skip=true -Drat.skip=true -Dmaven.javadoc.skip=true -B
```

Для Windows:
```
mvnw.cmd clean install -Prelease -T1C -DskipTests -Djacoco.skip=true -Dcheckstyle.skip=true -Drat.skip=true -Dmaven.javadoc.skip=true -B
```

### Сборка проекта для тестирования SQL

<hr>

#### "Ручная" сборка

<hr>

В первую очередь также необходимо скачать [проект](https://github.com/TedCraft/FirebirdShardingSQLComparingTool) с github (сейчас вы находитесь на его странице).

После этого необходимо в папке с проектом выполнить команду для "упаковки" проекта в jar-файл.

Для linux:

```
./mvnw pacakge
```

Для Windows

```
mvnw.cmd pacakge
```

После этого jar-файл появится в папке target: `SQLStandComparator-1.0-SNAPSHOT.jar`.

Для запуска данного файла необходимо выполнить следующую комманду:

```
java -jar -Dfile.encoding=windows-1251 -jar SQLStandComparator-1.0-SNAPSHOT.jar
```

В конце выполнения данного файла результат сравнения сохраняется в файле `src/main/resources/results of comparing.html`.

<hr>

#### Сборка через файл

<hr>

Для начало необходимо перейти в папку сборки, в зависимости от вашей операционной системы (windows_build/linux_build).

Далее необходимо выполнить файл для сборки (windows_build.bat/linux_build.sh).

Для запуска проекта выполняется скрипт (start.bat/start.sh).

<hr>

#### Сборка через IntelliJ IDEA

<hr>

Для начала необходимо по аналогии с "ручной" сборкой выполнить maven-комманду package. Для этого при открытии проекта в IDEA необходимо справа нажать на иконку Maven и выбрать скрипт package.
![](img/package.png)

После этого необходимо создать конфигурацию (кнопка add configuration справа сверху). В качестве типа конфигурации выбрать Jar Application и указать путь до jar-файла: `target/SQLStandComparator-1.0-SNAPSHOT.jar`.

Для запуска проекта можно нажать на зелёный треугольник (обычный запуск) либо на зелёного жука (запуск в режим отладки).

### Форматирование исходного кода ShardingSphere

<hr>

Если используется IntelliJ IDEA, то в первую очередь необходимо загрузить кастомные профили для правильного форматирования кода и выявления ошибок.

Для этого необходимо: 
- Открыть настройки сочетанием клавиш Ctrl+Alt+S
- В меню Editor найти пункт Code Styles
- В нём сверху в подпункте Scheme нажать на шестерёнку и выбрать пункт Import scheme -> IntelliJ IDEA code style XML
- В открывшемся окне необходимо перейти в папку src/resources/idea и выбрать файл code-styles.xml
- Аналогичные действия необходимо проделать и с пунктом inspections

### Создание Pull Request 

<hr>

Перед созданием PR необходимо убедиться что доработки не конфликтуют с основным кодом проекта.

Для этого необходимо:
- Необходимо применить форматирование стиля кода с помощью команды ```./mvnw spotless:apply -Pcheck```
- Далее нужно провести чистую сборку проекта с помощью команды ```./mvnw clean install -B -T1C -Pcheck```. Если при сборке происходит ошибка конвертации, то необходимо поставить в системе регион США или Соединённое Королевство.

##
