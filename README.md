# snack-truck-orders

An android code sample. 

Imagine that you work for SnackTruck, which runs food trucks that sell a selection of snacks. Instead of having customers form one line,
SnackTruck wants to have a pool of phones and tablets each truck can pass around. That way, multiple people can order snacks at once. Your
job is to write the app that will go on those order-taking devices. This app will present a list of snacks which can be veggies [text in green color]
or non-veggies [text in red color] with a checkbox:
French-fries [ ]
Milk-shake [ ]
Chicken-burger [ ]
Veggie-burger [ ]
Priority One Use Case
The customer should be able to select snacks from the list, and submit orders.
A user can select a set of snacks.
Pressing a Submit button at the bottom of the UI will finish the order and show the summary (a list of selected snacks) in a dialog.
Once the order summary dialog is dismissed, all selections will return to default, preparing the app for the next order.
Note: The network service for actually placing the order isn't built yet, so just put a stub & comment where you'd put the call to send the order.
Priority Two Use Case
The customer should be able to filter the snack list by snack type.
There will be two checkboxes at the top of the app, Veggies and Non-Veggies. Both are checked by default.
When the Veggies checkbox is checked, Veggie snacks are shown in the main list. When the Veggies checkbox is unchecked, Veggie
snacks aren't shown. Same for Non-Veggie.
All requirements of the priority 1 use case are met.
Priority Three Use Case
The truck operator should be able to add more snacks to the list.
There must be an “add” action in the action bar at the top of the app
When a user selects the “add” action, a dialog is displayed.


The dialog should have a toggle for veggie/non-veggie, and a text field to enter the new snack name
The dialog should have “Save” and “Cancel” buttons.
When the user hits “Save”, the dialog is dismissed, and the user’s new snack is added to the list.
When the user hits “Cancel”, the dialog is dismissed.

Note: Using the app both for menu editing and for customer ordering presents a problem. The spec doesn't call for any mechanism to distinguish
an operator, who may both order things and edit the menu, from a customer, who may only order things. For bonus points, propose a design
change that would allow the app to serve both users. No need to code that, just speak to it.

Full list of starting snacks:
Veggie
French fries
Veggieburger
Carrots
Apple
Banana
Milkshake
Non-veggie
Cheeseburger
Hamburger
Hot dog
