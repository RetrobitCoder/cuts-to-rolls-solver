# TODO
[x] Create gui
    - 2 columns
        - [x] 1 for cuts and 1 for rolls
        - [ ] ~~want to be able to drag and drop rolls entries for priority~~ Little too much right now: https://stackoverflow.com/questions/20412445/how-to-create-a-reorder-able-tableview-in-javafx going to need to make custom cells for draggable
        - [x] Need to be able to add cuts and rolls
        - [x] want to be able to add about 100 items for cuts and 100 for rolls
        - [x] need to  handle when giving roll size being 0 or negative
        - [x] synchronized scrolling for cuts and rolls
        - [x] need to add a add/delete button or just leave it at 100 default
    - [x] area showing solver answers
[x] Update solver
    - [x] update solver to be able to go through roll list
    - [x]need to make unit tests for it
[x] Update textfield navigations so hitting enter moves to next line
[] Release binary
    - generate binaries (https://stackoverflow.com/questions/77591164/package-a-javafx-application-as-a-native-executable-with-resources)(also want to look at maven way too)