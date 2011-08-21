# Quaere
*Note: This project is not actively maintained. I'm just keeping it around for historical interest.*

Quaere is an open source, extensible framework that adds a querying syntax reminiscent of SQL to Java applications. Quaere allows you to filter, enumerate and create projections over a number of collections and other queryable resources using a common, expressive syntax.

### Selecting all integers that are less than five from an array of integers
	Integer[] numbers={5, 4, 1, 3, 9, 8, 7, 2, 0};
	Iterable<Integer> lowNumbers=from("n").in(numbers).
	         		  where(lt("n",5).
					  select("n");
	System.out.println("All numbers that are less than five:");
	for (Integer n: lowNumbers) {
		System.out.println(n); 
	}

Quaere detaches queries from the query API used by different queryable resources such as databases, catalogs and other structured data, allowing one language to be used to query numerous resources.

### Selecting all customers in the Washington region from a database
	// Setup a JPA entity manager...
	SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
	EntityManagerFactory entityManagerFactory = 
    new EntityManagerFactoryImpl(sessionFactory, PersistenceUnitTransactionType.RESOURCE_LOCAL, true);
	QueryableEntityManager entityManager = 
    		new QueryableEntityManager(entityManagerFactory.createEntityManager());

	// Select all customers in the Washington region
	Iterable<Customer> waCustomers =
        from("c").in(entityManager.entity(Customer.class)).
        where(eq("c.getRegion()", "WA")).
        select("c");

	System.out.println("These customers are located in the Washington region:");
	for (Customer c : waCustomers) {
    	System.out.println(c.getCompanyName());
	}

## Introduciton
To get started using Quaere, let's begin with a simple Java program.

### Quaere is a query language
The Quaere query language is an internal DSL written in Java. To achieve rich language integration without the need for preprocessors, code generators or similar, Quaere employs some unconventional programming techniques. Luckily, this is tucked away inside of the Quaere API and there is only one thing you'll have to do to bring the querying abilities of Quaere to your own applications - use a static import to get the language in place.

### Importing the Quaere query language
	import static org.quaere.DSL.*;

### Writing our first queries
Once we have the import statement in place, we're ready to write our first query. For this introduction we'll only be using the Quaere for Objects querying engine. Keep in mind that Quaere is a query language and that these queries would look exactly the same if other data sources, such as a JPA EntityManager, where queried.

#### Selecting everything from an array of strings
	public class GettingStartedWithQuaere {
	    publicstatic void main() {
	        String[] cities = {
	            "London",
	            "New York",
	            "Tokyo",
	            "Stockholm",
	            "Barcelona",
	            "Sydney"
	        };

	        Iterable<String> allCities =
	            from("city").in(cities).select("city");
	        for (String city: allCities) {
	            System.out.println(city);
	        }
	    }
	}

When we run this program, it will print:
	London
	New York
	Tokyo
	Stockholm
	Barcelona
	Sydney

As you can see, the Quaere query language resembles SQL, the difference is that it queries are written "backwards" - starting with the from clause and ending with a select clause. The primary reason for this is that in a Quaere query, data flows in order of the clauses. In the query above we start with defining an city alias for the cities array and then select each city into a new collection called *allCities*.

Let's move on to writing a query that restricts the results using a where-clause.

#### Selecting all the large populations
	public class GettingStartedWithQuaere {
	    publicstatic void main() {
	        Integer[] cityPopulations = {
	            13000000, // London
	            21903623, // New York
	            12570000,  // Tokyo
	            1932763,  // Stockholm
	            1605602,  // Barcelona
	            4119190   // Sydney
	        };

	        Iterable<Integer> largePopulations =
	            from("population").in(cityPopulations).
	            where(gt("population",10000000))
	            .select("population");
        
	        for (Integer population: largePopulations) {
	            System.out.println(population);
	        }
	    }
	}

When we run this program, the populations for London, New York and Tokyo will be printed. If you're familiar with Hibernate's Criteria API, you should recognize the *gt* operator. Quaere uses this shorthand form for greater than comparisons. The expression in the previous sample is analogous to *population > 10000000*.

Our code suffers from primitive obsession, so it's time to introduce a *City* domain object.

#### The City domain object
	// City.java
	public class City {
	    private final String name;
	    private final int population;
	    private final Continent continent;

	    public String getName() {
	        return name;
	    }
	    public int getPopulation() {
	        return population;
	    }
	    public Continent getContinent() {
	        return continent;
	    }

	    public City(String name, int population, Continent continent) {
	        this.name = name;
	        this.population = population;
	        this.continent = continent;
	    }

	    public String toString() {
	        return String.format(
	                "%s has a popluation of %s and is located in %s",
	                name,
	                population,
	                continent);
	    }
	    public static final City[] ALL_CITIES={
	        new City("London",13000000,Continent.EUROPE),
	        new City("New York",21903623,Continent.AMERICA),
	        new City("Tokyo",12570000,Continent.ASIA),
	        new City("Stockholm",1932763,Continent.EUROPE),
	        new City("Barcelona",1605602,Continent.EUROPE),
	        new City("Sydney",4119190,Continent.AUSTRALIA)
	    };
	}


	// Continent.java
	public enum Continent {
	    AMERICA,
	    EUROPE,
	    AFRICA,
	    ASIA,
	    AUSTRALIA
	}

Now, let's change our query so that we select the names of the large cities.

#### Selecting the names of cities with large populations
	public class GettingStartedWithQuaere {
	    public static void main() {
	        City[] cities=City.ALL_CITIES;

	        Iterable<String> largePopulations =
	            from("city").in(cities).
	            where(gt("city.getPopulation()",10000000)).
	            select("city.getName()");

	        for (String cityName: largePopulations) {
	            System.out.println(cityName);
	        }
	    }
	}

When we run this program, it will print the names London, New York and Tokyo. Let's dive into what this query does. Similar to our first example, we first define an alias for the elements in the cities array, then we define a restriction predicate that says that only the cities whose population is greater than ten million should be included in the result. Notice that we refer to the getter (getPopulation()) of the City class in our predicate. Finally we select the names of the cities who matched the predicate.
Now you've learned the basics of Quaere. Let's move on to using some more features.

### Unleashing the power of Quaere
Quaere supports most of the operations you're familiar with from SQL. In this section we'll learn how to use these to write more advanced queries.
#### Ordering
Just like with SQL, you can order the results of your queries using an order-by clause. A Quaere query results in a sequence of elements that are produced in some order that is intrinsic in the underlying information sources. Just like SQL, Quaere has operators for controlling the order of the query result. The most basic of these operators is the orderBy operator.

##### Let's order the cities by their population...
	public class GettingStartedWithQuaere {
	    public static void main() {
	        City[] cities=City.ALL_CITIES;

	        Iterable<City> largePopulations =
	            from("city").in(cities).
	            orderBy("city.getPopulation()").
	            select("city");

	        for (City city : largePopulations) {
	            System.out.println(city);
	        }
	    }
	}

When we run this example, the cities will be ordered by their population in ascending order.
	Barcelona has a popluation of 1605602 and is located in EUROPE
	Stockholm has a popluation of 1932763 and is located in EUROPE
	Sydney has a popluation of 4119190 and is located in AUSTRALIA
	Tokyo has a popluation of 12570000 and is located in ASIA
	London has a popluation of 13000000 and is located in EUROPE
	New York has a popluation of 21903623 and is located in AMERICA

Naturally, we can also sort the cities in descending order. To do this, simple change the orderBy keyword to orderByDescending.
We can also combine different ordering clauses. The example below sorts the cities by their names in ascending order and then by their population in descending order.

##### Using multiple order clauses
	public class GettingStartedWithQuaere {
	    public static void main() {
	        City[] cities=City.ALL_CITIES;

	        Iterable<City> largePopulations =
	            from("city").in(cities).
	            orderBy("city.getName()").
	            orderByDescending("city.getPopulation()").
	            select("city");

	        for (City city : largePopulations) {
	            System.out.println(city);
	        }
	    }
	}

#### Grouping

Quaere also includes a groupBy operator which imposes a partitioning over a sequence of elements based on a key extraction function. The groupBy operator creates a Group element for each distinct key that was encountered. Each Group contains the key and the group of elements that mapped to the key. 
Let's group our list of cities by continent...

##### Grouping query results
	public class GettingStartedWithQuaere {
	    public static void main() {
	        City[] cities=City.ALL_CITIES;

	        Iterable<Group> groups =
	            from("city").in(cities).
	            group("city").by("city.getContinent()").into("g").
	            select("g");

	        for (Group group : groups) {
	            System.out.println(group.getKey());
	            System.out.println(group.getGroup());
	        }
	    }
	}

When we run our program, we'll get this output:
	AUSTRALIA
	  [Sydney has a popluation of 4119190 and is located in AUSTRALIA]
	AMERICA
	  [New York has a popluation of 21903623 and is located in AMERICA]
	EUROPE
	  [London has a popluation of 13000000 and is located in EUROPE, Stockholm has a popluation of 1932763 and is located in EUROPE, Barcelona has a popluation of 1605602 and is located in EUROPE]
	ASIA
	  [Tokyo has a popluation of 12570000 and is located in ASIA]

Notice that the query produces a sequence of Group instances rather than City instances. The Group class is a special purpose class used to demote groups.

#### The Group class
	package org.quaere;

	import java.util.List;
	import java.util.ArrayList;

	public class Group {
	    private final Object key;
	    private final List<Object> group = new ArrayList<Object>();

	    public Group(Object key) {
	        this.key = key;
	    }

	    public List<Object> getGroup() {
	        return group;
	    }

	    publicObject getKey() {
	        return key;
	    }
	}
