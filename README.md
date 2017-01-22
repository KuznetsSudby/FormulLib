# FormulLib

## Начало

To Do

-------------------------------------------------

## Атрибуты у View

-------------------------------------------------

#### Маштабирование
```java
fs:min_scale format="float"
fs:max_scale format="float"
fs:autoscale format="boolean"
fs:autoscale_width format="boolean"
fs:movable_extrascale format="float"
```

Атрибуты отвечают за:

1)-2) границы min max коффициента маштабирования на экране. 

3) автомаштабирование

4) автомаштабирование ширины блока по внутреннему контенту (ширина двигаемых и изменяемых блоков вычисляется по самому длинному тексту)

5) увеличение блока при перемещении

=====================================

#### Размеры
```java
fs:block_size format="dimension"
fs:block_factor format="float"
fs:percent format="float"
fs:height format="dimension"
fs:percent_height format="boolean"
fs:padding_factor format="float"
fs:text_percent format="float"
fs:division_factor format="float"
fs:division_padding_factor format="float"
fs:movable_divider_factor format="float"
fs:group_movables format="boolean"
fs:check_size format="float"
```

Атрибуты отвечают за:

1) стандартный размер блока для расчета (его высота)

2) коэффициент ширины стандартного блока (относителльно block_size)

3)-5) расчет высоты части с формулой (относительно всей высоты View)

6) коэффициент отступов внтури поля (относителльно block_size)

7) коэффициент высоты текста (относителльно block_size)

8) - 9) коэффициенты высоты линии деления и ее отступов (относителльно block_size)

10) коэффициент свободного пространства между двигаемыми блоками (относителльно block_size)

11) группировку (по центру или растягивать на всю ширину)

12) коэффициет размера большого знака результата (относительно высоты части с формулой)

=====================================

#### Цвета
```java
fs:background_color format="color"
fs:text_color format="color"
fs:division_color format="color"
```

Атрибуты отвечают за:

1) цвет фона

2) цвет текста

3) цвет линии деления

=====================================

#### Drawables
```java
fs:default_block format="reference"
fs:changeable_block format="reference"
fs:movable_block format="reference"
fs:unselected_block format="reference"
fs:good_block format="reference"
fs:bad_block format="reference"
fs:plus_block format="reference"
fs:minus_block format="reference"
fs:equally_block format="reference"
fs:bkt_left_block format="reference"
fs:bkt_right_block format="reference"
fs:multiply_block format="reference"
fs:division_block format="reference"
fs:check_bad format="reference"
fs:check_good format="reference"
```

Атрибуты отвечают за:

1)-3) фон блоков

4)-6) фон подсветки блоковблоков

7)-13) изображения для символов

14)-15) изображения большых знаков результата

-------------------------------------------------

## Работа с парсером формул

To Do

## Типы Leaf

To Do

-------------------------------------------------

## Основные методы

To Do

-------------------------------------------------

### Copyright (c) 2016 FastFlow team

Полный текст лицензии содержится в файле License.md
