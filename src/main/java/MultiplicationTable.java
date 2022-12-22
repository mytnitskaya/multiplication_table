import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {

        int dim = getDimFromUser();

        int firstCellWidth = String.valueOf(dim).length();
        int cellWidth = String.valueOf(dim*dim).length();

        String separatorLine = generateSeparatorLine(dim, firstCellWidth, cellWidth);

        for (int row = 0; row <= dim; row++){
            for (int column = 0; column <= dim; column++){
                String value = getValue(row, column);
                var isFirstColumn = column == 0;
                var currentWidth = isFirstColumn ? firstCellWidth : cellWidth;
                var isLastColumn = column == dim;
                var horizontalDivider = isLastColumn ? "" : "|";
                System.out.print(generateCell(value, currentWidth)+horizontalDivider);
            }
            System.out.println();
            System.out.println(separatorLine);
        }
    }

    static int getDimFromUser(){
        var input = new Scanner(System.in);
        var continueInput = true;
        int dim = 0;

        do {
            try{
                System.out.print("Enter the dimension of the multiplication table: ");
                dim = input.nextInt();
                if (dim < 1){
                    throw new Exception("Dimension must be greater than 0");
                }
                System.out.println("Multiplication table with dimension:  " + dim);

                continueInput = false;
            }
            catch (InputMismatchException ex) {
                System.out.println("Incorrect input: an integer is required");
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
            }
            finally {
                input.nextLine();
            }
        }
        while (continueInput);

        input.close();
        return dim;
    }

    static String generateSeparatorLine(int dim, int firstCellWidth, int cellWidth){
        final String divider = "-";
        String firstSeparatorCell = divider.repeat(firstCellWidth)+"+";
        String separatorCells = (divider.repeat(cellWidth)+"+").repeat(dim-1);
        String lastSeparatorCell = divider.repeat(cellWidth);
        return firstSeparatorCell + separatorCells + lastSeparatorCell;
    }

    static String generateCell(String value, int cellWidth){
        var formatPattern = "%" + cellWidth + "s";
        return String.format(formatPattern, value);
    }

    static String getValue(int row, int column){
        if (row == 0 && column ==0){
            return "";
        }
        if (row == 0 || column == 0) {
            return Integer.toString(row + column);
        }
        return Integer.toString(row*column);
    }

}
