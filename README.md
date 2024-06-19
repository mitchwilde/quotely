# Quotely
Description:
Quotely is your friendly quote-fetching app!  
Given a language, English or Russian, 
this command line Java application will retrieve a random quote in that language 
from the Forismatic API found here: https://forismatic.com/en/api/

## How To Install and Run
- Because Quotely is a self-contained uber JAR, no installation is necessary.

### Steps
1. Download and unzip `quotely-[version].zip`
2. Run the OS-specific run script found in `quotely-[version]/bin` (see "Usage")
   
### Usage
#### Unix/macOS
`./quotely <language>`*
#### Windows
`./quotely.bat <language>`*
*Required: `<language>` = `en` or `ru`

#### Example
`% ./quotely en
Neither genius, fame, nor love show the greatness of the soul. Only kindness can do that.  – Jean Lacordaire`

## How To Set Up Quotely for Development and Testing
- Download/clone source code:
- Build: `./gradlew build`
- Run: 
  - English quote -> `./gradlew runEng` OR `./gradlew run --args="en"`
  - Russian quote -> `./gradlew runRus` OR `./gradlew run --args="ru"`
- Run Tests: `./gradlew test`

## Design Considerations
- Quotely currently runs synchronously using HttpClient (Java 22)
- Asynchronous calls to the Forismatic API could be easily implemented if the project were expanded to include a feature to fetch multiple quotes.