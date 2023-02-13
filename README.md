# SoftStream Test Task

## Backend: Java 17, Spring Boot 3, MySQL, Hibernate.

### Frontend: https://github.com/BohdanBodakva/car_catalogue_SoftStream_frontend

Write a web application "Car catalog".
The main requirements for the project:
The car must have the following characteristics: make, model, color, engine volume, price, description (optional field).
 The price of the car must be valid from the moment it is set (there must be a field with the date the price is valid), so that we can see how much the specified car cost at different times (for example, six months ago).
The application must have the following elements:

• Directory of car brands (viewing, adding, deleting, editing);


• Directory of car models of the specified brand (review, addition, deletion, editing);


• Adding a new car;


• Editing an existing car.


• Car catalog view page:


1)  The car catalog view page should have a tree of car brands, with fields to filter by car color (auto-complete field), engine size (auto-complete field), and price range indicating the date for which the price should be taken.
2)  When selecting a specific model or a specific brand in the car model tree, the search must be performed according to the model or brand.
3)  Found cars should be placed on a tile, the user should be able to change the number of columns in the tile and the total number of elements represented in the tile, by default this value is columns*5, where columns is the number of columns in the tile.


• The user must be able to move between screens freely.


When creating, use: Angular 7+, MS SQL Server or MySQL database.
