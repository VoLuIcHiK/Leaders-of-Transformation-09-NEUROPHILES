## <p align="center"> ЗАДАЧА №9 ИНТЕРАКТИВНАЯ ПЛАТФОРМА ДЛЯ МОНИТОРИНГА ВНУТРЕННЕЙ ОТДЕЛКИ КВАРТИРЫ  </p>
<p align="center">
<img width="743" alt="photo" src="https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/6f6e9ae0-6cec-4054-963f-833040f76ae2">

</p>


*Состав команды "НЕЙРОФИЛЫ"*   
*Чиженко Леон (https://github.com/Leon200211) - Fullstack-разработчик*    
*Сергей Куликов (https://github.com/MrMarvel) - Mobile/Backend-разработчик*  
*Карпов Даниил (https://github.com/Free4ky) - ML-engineer*  
*Валуева Анастасия (https://github.com/VoLuIcHiK) - Data Engineer/Designer*   
*Козлов Михаил (https://github.com/Borntowarn) - ML-engineer*  

## Оглавление
1. [Задание](#1)
2. [Решение](#2)
3. [Работа модели](#11)
4. [Результат разработки](#3)
6. [Уникальность нашего решения](#5)
7. [Стек](#6)
8. [Запуск](#7)
9. [Интерфейс приложения](#8)
10. [Ссылка на сайт](#9)
11. [Ссылка на google drive](#10)

## <a name="1"> Задание </a>

Разработать сервис, позволяющий мониторить процессы внутренней отделки строящихся зданий, который включает:
- разработку ПО, которое позволит собирать данные для автоматизации определения квартир / этажа;
- продуктивное решение для анализа степени готовности квартиры, наличие строительного мусора и т.д. на основе анализа видеопотока;
- сравнение полученных значений с плановыми показателями выполнения работ, расчёт отклонений от плана.

## <a name="2">Решение </a>

Для определения местоположения и анализа готовности помещения мы решили придумали алгоритм, представленный на фото ниже.
<p align="center">
<img width="600" alt="image" src="https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/24ccfeda-c71c-459e-80cb-94aa9160179e">
</p>

Ниже представленна физическая модель нашей базы данных: 
<p align="center">
<img width="600" height="400" alt="image" src="https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/79ae1834-bd4f-4bb1-b2f4-fcf276de2bb5">
</p>


## <a name="11">Работа модели </a>
Ниже представлены скрины работы нашей обученной модели:
<p align="center">
<img width="600" height="400" alt="image" src="https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/5bcdd6ba-530b-48c6-baad-c277a073a2c6">
<img width="600" height="400" alt="image" src="https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/62fca6c1-a7cc-4940-82f4-19d2f535dc20">
<img width="600" height="400" alt="image" src="https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/0fa6ee2b-85aa-4e6f-880f-777be0685446">
</p>

## <a name="3">Результат разработки </a>

В ходе решения поставленной задачи нам удалось разработать мобильное приложение, которое имеет следующий функционал:
1. Запись видео обхода помещения;
2. Анализ записанного видео;
3. Расчет степени готовности внутренней отделки в каждой комнате, квартире, а также на этаже;
4. Просмотр информации по проведенным ранее обходам во всех ЖК;
5. Возможность редактирования информации вручную, полученную от датчиков (при определении ЖК, дома, секции, этажа) или в ходе анализа видео;
6. Автоматическо заполнение "шахматок" и возможность их скачивания;
7. Редактирование информации в профиле;
8. Просмотр ответов на часто задаваемые вопросы в разделе "Помощь".

Помимо мобильного приложения мы реализовали сайт, на котором удобно просматривать и демонстрировать степень готовности внутренней отделки.

Созданное нами решение поможет автоматизировать процесс мониторинга внутренней отделки квартир и МОП.


## <a name="5">Уникальность нашего решения </a>

- Обработка видео и получение результата происходит по кадрам на самом устройстве прямо во время съёмки (в real-time);
- Рассчет процента готовности для каждой комнаты/квартиры и вывод результата на экран прямо во время съемки;
- Достигнута высокая точность опредления местоположения внутри здания за счет автоматического определения ЖК, дома, секции, этажа, квартиры с помощью GPS и барометра, ручного выбора комнаты и возможности правки всех этих значений вручную прямо во время съёмки видео;
- Высокое качество работы модели детекции объектов/отделки (YOLOv8);

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
Для запуска мобильного приложения необходимо:
1. Скачать с нашего репозитория APK;
2. Дождаться успешной установки приложения;
3. Открыть приложение, нажав на появившуюся иконку.

## <a name="8">Интерфейс приложения </a>
https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/36cf550d-4740-421e-85bc-f42d60641680


## <a name="9">Ссылка на сайт </a>
- [Ссылка на наш сайт "СтройКонтроль"](http://u1988986.isp.regruhosting.ru/login)
<p align="center">
<img width="600" height="400" alt="Главный экран" src="https://github.com/VoLuIcHiK/Leaders-of-Transformation-09-NEUROPHILES/assets/90902903/df928807-da16-45e4-b9cc-8eec3b2a9126">
</p>

## <a name="10">Ссылка на google drive </a>
- [Ссылка на google drive](https://drive.google.com/drive/folders/1gQXvqQinVik9TC0aIwWMTdtwKi4GV6qq)
