# Gilded Rose Refactoring Kata — Java

The goal of this exercise is to add a new feature (support for Conjured items) in legacy code while refactoring safely
without breaking the existing behavior.

## Run the project

There are two types of tests, you can run them following these instructions:

For unit tests, cd to the Java folder and run
``mvn test``

For the Golden Master tests, run
``./start_texttest.sh``

## Followed approach

The first thing to do was to add characterization tests (GildedRoseTest) before touching any code to have a solid and
safe way to check that the behavior of the code stays valid.
The existing golden master (texttests/ThirtyDays) is a second safety net to ensure the existing behavior stays
untouched.

The goal of the characterization tests is to ensure that the rules defined for the evolutions of the quality of the
items are correct, by testing normal cases, but also boundary cases.

The golden master test is responsible for ensuring that the behavior is working across multiple calls to the
updateQuality() method. It guarantees that the evolution of the quality of the items is working properly on multiple
days. The golden master also covers items never tested individually in GildedRoseTest (+5 Dexterity Vest,
Elixir of the Mongoose), so it catches regressions the unit tests can't see.

The TDD (Test Driven Development) was applied for adding the Conjured item support.

I refactored the application in multiple steps (one commit by step)
- Fork of the initial project on Github
- Fix existing failing test
- Setup a well structured approach for unit tests (Arrange-Act-Assert)
- Add characterization tests for the existing behavior
- Configure the golden master (texttests) to run against the Java implementation
- Extract methods in existing code
- Introduce the strategy and factory pattern
- Remove legacy code
- Encapsulate the items field behind a defensive accessor
- Add Javadoc to key classes
- Add failing tests for the Conjured items
- Fix a stale Gradle classpath silently shadowing Maven classes in texttest
- Add support for the Conjured items

Each step was checked against both nets before moving to the next one, so any regression would show up on the smallest
possible diff.

## Conception choices

### Strategy + Factory
The strategy pattern fits perfectly here where we have different items with different behavior. Isolating each behavior
by strategy improves the segmentation and readability of the code. Moreover, it allowed one to add a new behavior just
by adding a new strategy; that's exactly what I did to add the support for Conjured items.

The factory pattern isolates the construction / instantiation of the right strategy for a given item based on its name.
So the whole logic for determining the correct strategy is in one single place.

### Template Method (`AgingItemUpdater`)

`AgingItemUpdater` is an intermediate abstract class that holds the behavior shared among multiple strategies.

The shared behavior is: adjust the quality based on the current sellIn, then decrement sellIn. Sulfuras never changes at
all, so it's the only strategy not extending `AgingItemUpdater`.

### Package `com.gildedrose.updater` and visibility

I decided to place all strategies in a package to organise the code properly. By also placing the factory in this
package, the visibility of the strategies can be kept package private, so only the factory and the ItemUpdater are
public.

### Encapsulation of `items`

I decided to encapsulate the field `items` of the `GildedRose` class to avoid exposing the reference of the array and to
prevent outside code from adding or removing items from the array kept in this class. It's a good defensive practice.
An accessor method has been added to allow one to retrieve the items in this array. A getter that just returns the array
wouldn't actually protect anything, the caller could still mutate it. That's why both the constructor and items() clone
the array before storing/returning it.
This change has been done after the
structural refactor because no ItemUpdater class touches GildedRose.items, so it's completely independent of the
Strategy/Factory refactor.


## Note regarding the spec
Ambiguity in GildedRoseRequirements_fr.md regarding the doubling of Aged Brie after expiration, and why the actual
behavior of the code was preserved. Indeed, the specification does not mention that the quality of the Aged Brie gets
increased by 2 after expiration, however the existing behavior of the application was doing so, that's the reason I also
kept this behavior.

## Tooling issue found along the way

While validating the Conjured feature against the golden master, `./start_texttest.sh` kept reporting
`succeeded` even though the behavior had just changed. The tests should have produced a visible diff.

The cause: `texttests/environment.gr` listed the Gradle build output (`build/classes/java/...`) before the
Maven output (`target/classes`) in the CLASSPATH. A stale `GildedRose.class`, compiled by Gradle before the
Strategy refactor even started, was silently shadowing the (up-to-date) Maven classes. So the golden master
had been testing old code for several commits without me noticing. To prevent this issue I decided to remove
the Gradle paths from `environment.gr`.

## What was deliberately left out
I tried to keep the code proportional to the size of the problem, and avoided adding structure that has no added value:
- No null-check validation in the constructor because nothing in the codebase calls it with null
- Item names are kept as plain String constants matched in a switch, not an enum (it would also work but add an
extra layer)
- The factory instantiates a new strategy on every call to updateQuality() (adding cache would add complexity to
the app)
- The updater package stays flat (no sub packages) as it only contains a few classes

