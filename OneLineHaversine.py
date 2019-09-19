'''This project involved calculating the distance between the Statue of Liberty and Notre Dame.'''
#By Dean Connell
print("The distance between the Statue of Liberty and Notre Dame is " + str(__import__('haversine').haversine((40.6892, 74.0445), (48.8530, 2.3499))) + " km")