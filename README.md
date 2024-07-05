# android-map-keyword

## Save Search word

### Database Requirements

Using `SQLite` to save data
- When application is restart, data is maintained
- Column: name, location, category

### Layout Requirements

**Save search word**

Input search word

- Text input window to search
- It has x button to erase

Saved search word list

- It has x button to erase
- It scrolls  horizontally

Search result list

- Using `RecyclerView` to implement about search result list
- It scrolls vertically

### Function List

**Save search word**

Requirements Rule
- Apply the MVVM architectural pattern

Input search word

- Search every time a character is entered
- When clicked x button, string is erased

Saved search word list

- Words are not duplicated and recently serached word are added later
- When clicked x button, saved search word is erased

Search result list

- Search results have search word as categories
- Selected item is added to the saved search word list