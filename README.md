## <p align="center"> ЗАДАЧА №9 ИНТЕРАКТИВНАЯ ПЛАТФОРМА ДЛЯ МОНИТОРИНГА ВНУТРЕННЕЙ ОТДЕЛКИ КВАРТИРЫ  </p>
<p align="center">
<img width="743" alt="photo" src="https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/25364f68-ae39-4eb4-aef5-40316ef9cd76">
</p>


*Состав команды "НЕЙРОФИЛЫ"*   
*Чиженко Леон (https://github.com/Leon200211) - Frontend-разработчик*    
*Сергей Куликов (https://github.com/MrMarvel) - Mobile/Backend-разработчик*  
*Карпов Даниил (https://github.com/Free4ky) - ML-engineer*  
*Валуева Анастасия (https://github.com/VoLuIcHiK) - ML-engineer/Designer*   
*Козлов Михаил (https://github.com/Borntowarn) - ML-engineer*  

## Оглавление
1. [Задание](#1)
2. [Решение](#2)
3. [Результат разработки](#3)
4. [Пример работы](#4)
5. [Уникальность нашего решения](#5)
6. [Стек](#6)
7. [Запуск](#7)
8. [Интерфейс приложения](#8)
9. [Ссылка на сайт](#9)

## <a name="1"> Задание </a>

Разработать сервис, позволяющий мониторить процессы внутренней отделки строящихся зданий, который включает:
- разработку ПО, которое позволит собирать данные для автоматизации определения квартир / этажа;
- продуктивное решение для анализа степени готовности квартиры, наличие строительного мусора и т.д. на основе анализа видеопотока;
- сравнение полученных значений с плановыми показателями выполнения работ, расчёт отклонений от плана.

## <a name="2">Решение </a>

Для определения местоположения и анализа готовности помещения мы решили придумали алгоритм, представленный на фото ниже.
<p align="center">
<img width="356" alt="image" src="https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/392433b2-456d-47bb-9202-e7b8f9f7338b">
</p>



## <a name="3">Результат разработки </a>

В ходе решения поставленной задачи нам удалось разработать мобильное приложение, которое имеет следующий функционал:
1. Запись видео обхода помещения;
2. Анализ записанного видео;
3. Расчет степени готовности внутренней отделки в каждой комнате, квартире, а также на этаже;
4. Просмотр информации по проведенным ранее обходам во всех ЖК;
5. Редактирование информации вручную, полученную от датчиков или в ходе анализа видео;

Созданное нами решение поможет автоматизировать процесс мониторинга внутренней отделки квартир и МОП.

### <a name="4">Пример работы</a>




## <a name="5">Уникальность нашего решения </a>

- Обработка записанного видео происходит в real-time на самом устройстве;
- Рассчитывается процент готовности для каждой комнаты/квартиры и выводится на экран пользователя прямо во время съемки;
- За счет автоматического определения местоположения (ЖК, дом, секция, этаж, квартира), ручного выбора комнаты и возможности правки их значений вручную прямо во время съёмки видео позволило добиться высокой точности полученных результатов;

## <a name="6">Стек </a>
<div>
  <img src="https://github.com/devicons/devicon/blob/master/icons/mysql/mysql-original-wordmark.svg" title="MySQL"  alt="MySQL" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/python/python-original-wordmark.svg" title="Python" alt="Puthon" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/androidstudio/androidstudio-plain.svg" title="android-studio" alt="android-studio" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original-wordmark.svg" title="Java" alt="Java" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/cplusplus/cplusplus-line.svg" title="Cplusplus" alt="Cplusplus" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/php/php-original.svg" title="php" alt="php" width="40" height="40"/>&nbsp;
  <img src="https://github.com/devicons/devicon/blob/master/icons/kotlin/kotlin-original-wordmark.svg" title="kotlin" alt="kotlin" width="40" height="40"/>&nbsp;

  

## <a name="7">Запуск </a>
Установить библиотеки. Есть 3 возможности запуска:
- Запуск десктопной версии. Для этого необходимо установить зависимости (в папке qtpure) `pip install -r requirements.txt`. Затем скачать Releases версию и запустить exe.
- Запуск на хостинге http://f0798611.xsph.ru/ (все развернуто на MTS Cloud). Доступно только 1 видео для демонстрации.
- Локальный запуск нейросети. Необходимо загрузить папку nn, установить зависимости, создать папку inference_videos/videos и положить туда видео. После обработки готовый файл появится в films_with_audiodescr.

Команда для запуска (source) из папки проекта
`py app.py`  
Также есть возможность запустить приложение в виде .exe файла.

## <a name="8">Интерфейс приложения </a>

<img width="400" height="600" alt="image" src="https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/88b13de0-2957-426c-be89-3658a13db2cf">

## <a name="9">Ссылка на сайт </a>
- [Ссылка на страницу сайта с нашим решением](http://f0798611.xsph.ru/)
