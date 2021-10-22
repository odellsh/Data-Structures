import java.util.Scanner;

public class MolecularMass{
    private int hydrogen = 1; 
    private int carbon = 12; 
    private int oxygen = 16;
    private int open_paren = 0;
    private int closed_paren = 0;
    
    private IntStack stack = new IntStack();

    public int molecule_Stack(String molecule){
        int poppedMol;

        for(int i = 0; i < molecule.length(); i++){
            if (molecule.charAt(i) == 'H'){
                stack.push(hydrogen);             
            }
            else if(molecule.charAt(i) == 'C'){
                stack.push(carbon);
            }
            else if(molecule.charAt(i) == 'O'){
                stack.push(oxygen);
            }
            else if(molecule.charAt(i) == '('){
                stack.push(open_paren);
            }
            else if(molecule.charAt(i) == ')'){
                int tempVariable = 0;
                int currentMol = stack.pop();
                while(currentMol != open_paren){
                    tempVariable += currentMol;
                    currentMol = stack.pop();
                }
                stack.push(tempVariable);
            }
            else if(molecule.charAt(i) == '2'){
                int t;
                t = stack.pop();
                t *= 2; //multiplies the molecule # times 2
                stack.push(t); //pushes that number back on
            }
            else if(molecule.charAt(i) == '3'){
                poppedMol = stack.pop();
                poppedMol *= 3;
                stack.push(poppedMol);
            }
            else if(molecule.charAt(i) == '4'){
                poppedMol = stack.pop();
                poppedMol *= 4;
                stack.push(poppedMol);
            }
            else if(molecule.charAt(i) == '5'){
                poppedMol = stack.pop();
                poppedMol *= 5;
                stack.push(poppedMol);
            }
            else if(molecule.charAt(i) == '6'){
                poppedMol = stack.pop();
                poppedMol *= 6;
                stack.push(poppedMol);
            }
            else if(molecule.charAt(i) == '7'){
                poppedMol = stack.pop();
                poppedMol *= 7;
                stack.push(poppedMol);
            }
            else if(molecule.charAt(i) == '8'){
                poppedMol = stack.pop();
                poppedMol *= 8;
                stack.push(poppedMol);
            }
            else if(molecule.charAt(i) == '9'){
                poppedMol = stack.pop();
                poppedMol *= 9;
                stack.push(poppedMol);
            }
        }
    return 0;
    }
    public int findMass(){
        int sum = 0;
        int tempVariable = stack.pop();
        while(tempVariable != -1){
            sum += tempVariable;
            tempVariable = stack.pop();
        }
        return sum;
    }
    
    public static void main(String[] args){
        
        MolecularMass m = new MolecularMass();
        Scanner massInput = new Scanner(System.in);
        System.out.print("Enter the molecule: "); 
        String molecule = massInput.nextLine();
        m.molecule_Stack(molecule);
        System.out.println("The Molecular Mass of " + molecule + " is " + m.findMass());
    }
}
