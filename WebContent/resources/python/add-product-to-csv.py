# Import writer class from csv module
from csv import writer
import sys

# Get the product_id from the argument
product_id = str(sys.argv[1])

# Open our existing CSV file in append mode
# Create a file object for this file
with open('D:\\JAVA\\Eclipse\\Doanthuctap\\webbantivi\\WebContent\\resources\\python\\dataFavorite\\products.csv', 'a', newline='') as f_object:

    # Pass this file object to csv.writer()
    # and get a writer object
    writer_object = writer(f_object)

    # Write the product_id as a new row
    writer_object.writerow([product_id])
    # Close the file object
    f_object.close()

print([product_id])
