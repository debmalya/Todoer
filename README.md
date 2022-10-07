# Requirements 
[Requirement for ToDo](https://github.com/todotxt/todo.txt)

# Technical Stack
* Java 11

```
	<properties>
		<maven.compiler.source>11</maven.compiler.source>
		<maven.compiler.target>11</maven.compiler.target>
	</properties>
```

* Apache Maven 3.8.6 (84538c9988a25aec085021c365c560670ad80f63) onwards

# How to run it?

##### mvn compile exec:java -Dexec.mainClass="west.de.main.Main" -Dexec.arguments="./src/test/resources/ToDo.txt"

It will print following output

```
Imported ToDo from file ./src/test/resources/ToDo.txt total task imported 14. Arranged by priority below:
(A) x Find ticket prices
(A) Call Mom
(A) Call Mom +Family +PeaceLoveAndHappiness @iphone @phone
(A) Thank Mom for the meatballs @phone
(B) Schedule Goodwill pickup +GarageSale @phone
(Z) do exercise
xylophone lesson
X 2012-01-01 Make resolutions
Really gotta call Mom (A) @phone @someday
(b) Get back to the boss
(B)->Submit TPS report
2011-03-02 Document task format +TodoTxt
Email SoAndSo at soandso@example.com
Post signs around the neighborhood +GarageSale
x 2011-03-03 Call Mom
x 2011-03-02 2011-03-01 Review Tim's pull request +TodoTxtTouch @github
x 2011-03-02 2011-03-01 Review submitter's code +TodoTxtTouch @github pri:A
```
##### first incomplete top priority jobs will be printed according to priority. 
##### Completed jobs will be printed at last

##### Then it print ToDos with project item '+GarageSale'

```
(B) Schedule Goodwill pickup +GarageSale @phone
Post signs around the neighborhood +GarageSale
```

##### Then it print ToDos with contextual item '@phone'

```
(A) Call Mom +Family +PeaceLoveAndHappiness @iphone @phone
(A) Thank Mom for the meatballs @phone
(B) Schedule Goodwill pickup +GarageSale @phone
Really gotta call Mom (A) @phone @someday
```

For testing used [Todo.txt](./src/test/resources/ToDo.txt)