# Quotely
Description:
Quotely is your friendly quote-fetching app!  
Given a language, English or Russian, 
this command line Java application will retrieve a random quote in that language 
from the Forismatic API found here: https://forismatic.com/en/api/

## How To Install and Run
- The inclusion of the Gradle wrapper scripts precludes the need for installation
- A self-contained uber JAR is included as well (see below for instructions).

### Steps to Run
1. Download and unzip `dist/quotely-[version].zip`
2. Run the OS-specific run script found in `quotely-[version]/bin` (see "Usage")
3. Alternatively, from `quotely-[version]/lib/` run `java -jar quotely-[version].jar <language>`*
   
### Usage
#### Unix/macOS
`% ./quotely <language>`*
#### Windows
`$ ./quotely.bat <language>`*

***Required**: `<language>` = `en` or `ru`

#### Example
`% ./quotely en
Neither genius, fame, nor love show the greatness of the soul. Only kindness can do that.  – Jean Lacordaire`

## How To Set Up Quotely for Development and Testing
- Download/clone source code:
- Build: `./gradlew build`
- Run: 
  - English quote -> `./gradlew runEng` OR `./gradlew run --args="English"`
  - Russian quote -> `./gradlew runRus` OR `./gradlew run --args="Russian"`
- Run Tests: `./gradlew test`

## Design Considerations
- Quotely currently runs synchronously using HttpClient (Java 22)
- Asynchronous calls to the Forismatic API could be easily implemented if the project were expanded to include a feature to fetch multiple quotes.


## To-Do
1. Default to English if no args
2. Add Validation class
3. Reduce main class bloat -> create separate processor/service class
4. Create interface for Forismatic API (Done)
5. Add check for Forismatic service availability/address potential Forismatic API failure
6. Add Logger
7. Add Javadocs to each method
8. Place property files outside of jar
9. Consider using a factory design pattern or AOP
10. Write one test per method
11. Consider converting to Maven