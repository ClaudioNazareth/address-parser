# Address Parser


An address provider returns addresses only with concatenated street
names and numbers. Our own system on the other hand has separate
fields for street name and street number.

**Input:** _string of address_
**Output:** _string of street and string of street-number_

1. **Write a simple program that does the task for the most simple
cases, e.g.**
  * a. “Winterallee 3” --> {“ Winterallee”, “3”}
  * b. “Musterstrasse 45” --> { “Musterstrasse”, “45”}
  * c. “Blaufeldweg 123B” --> {“Blaufeldweg”, “123B”}
  
2. C**onsider more complicated cases**
  * a. “Am Bächle 23” --> {“Am Bächle”, “23”}
  * b. “Auf der Vogelwiese 23 b” --> {“Auf der Vogelwiese”, “23 b”}

3. **BONUS: Consider other countries (complex cases)**
  * a. “4, rue de la revolution” --> {“rue de la revolution” ,"4”}  
  * b. “200 Broadway Av” --> “Broadway Av",“200”}
  * c. “Calle Aduana, 29” --> {“Calle Aduana”, “29”}
  * d. “Calle 39 No 1540” --> {“Calle 39”, “No 1540”}