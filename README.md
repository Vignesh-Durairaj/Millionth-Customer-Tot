# Millionth Customer Tote price

Just another `Java` based solution for a challenging scenario where a shopping tote of defined dimensions where given and a list of items along with its own dimensions, so that the tote can be filled with the most aggregate valued products that fits in the tote. Refer this [this page](http://geeks.redmart.com/2015/10/26/1000000th-customer-prize-another-programming-challenge/). 

* Programming Language used : `Java` - version 8
* `Map.txt` has been parsed into a two dimensional array of integer with
  * Heavy use of `Java 8 Streams`
  * Implementation of functional interfaces using `Lambdas` and `Method References`

**Solution Approach**
---
1. Used `Java 8 Streams` to parse the input **CSV** file into **List<Products>**
2. Filter out the products whose dimensions exceeds that of the tote. This is applicable for **Any of the dimensions**
3. Create a `Map` containing the current tote contents, its net price and weight against it occupied volume
4. Iterate for each of the product and start fill up the tote and check its current tote content's nett volume is less than that of tote and a similar volumed tote-contents are already there in the map
   1. If not exists add the map with the new volume and the new tote-contents 
   2. Else check for the maximum priced tote-content, else for same price, check for the minimal weight and update the map
5. After iterating for all the contents in the product CSV, get the max priced item in the map.
 
> Look for the simplest and the shortest solution for any problem, rather than complicating it further
