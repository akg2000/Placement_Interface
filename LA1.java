import java.util.*;


class Student{
    private final int roll;
    private final String branch;
    private final float cgpa;
    private String placement_status;

    Student(int roll, float cgpa, String branch, String placement_status){
        this.roll=roll;
        this.branch=branch;
        this.cgpa=cgpa;
        this.placement_status=placement_status;

    }

    public String getbranch(){
        return this.branch;
    }
    public int getroll(){
        return this.roll;
    }
    public float getgpa(){
        return this.cgpa;
    }

    public void show_student_details(){
        System.out.println(this.roll);
        System.out.println(this.cgpa);
        System.out.println(this.branch);
        System.out.println("Placement Status : "+this.placement_status);
    }
}

class Company{
    private final String company_name;
    private int number_of_required_students;
    private String application_status;
    private ArrayList<String> eligiblecourses_arr;
    private int number_of_eligible_courses;
    private int[][] techincalscores;
    private int total_students;

    Company(String company_name,int number_of_eligible_courses,ArrayList<String> eligiblecourses_arr,int number_of_required_students,int number_of_students){
        this.company_name=company_name;
        this.number_of_eligible_courses=number_of_eligible_courses;
        this.eligiblecourses_arr=eligiblecourses_arr;
        this.number_of_required_students=number_of_required_students;
        this.application_status="OPEN";
        this.total_students=number_of_students;
        this.techincalscores = new int[2][number_of_students];
    }

    public int getnumber_of_required_students(){
        return number_of_required_students;
    }
    public int gettotal_students(){
        return this.total_students;
    }
    public int[][] gettechnicalscores(){
        return this.techincalscores;
    }
    public ArrayList<String> geteligiblecourses_arr(){
        return this.eligiblecourses_arr;
    }
    public String getapplication_status(){
        return this.application_status;
    }
    public String getcompany_name(){
        return this.company_name;
    }
    public void setapplicationstatus(){
        this.application_status="CLOSED";
    }

    public void show_company_details(){
        System.out.println(this.company_name);
        System.out.println("Course Criteria");
        for(int i=0;i<this.eligiblecourses_arr.size();i++){
            System.out.println(this.eligiblecourses_arr.get(i));
        }
        System.out.println("Number of Required Students = "+this.number_of_required_students);
        System.out.println("Application Status = "+ this.application_status);
    }
}

class Placement_Office{
    private ArrayList<Student> ongoingplacements_arr;
    private ArrayList<Student> placedstudents_arr;
    private ArrayList<Student> students_arr;
    private ArrayList<Company> company_arr;

    Placement_Office(){
        placedstudents_arr = new ArrayList<Student>();
        ongoingplacements_arr = new ArrayList<Student>();
        students_arr = new ArrayList<Student>();
        company_arr = new ArrayList<Company>();
    }
    public void add_student(Student std){
        ongoingplacements_arr.add(std);
        students_arr.add(std);
    }
                    
    public void add_company(Company comp){
        company_arr.add(comp);
    }
    public ArrayList<Student> getongoingplacements_arr(){
        return ongoingplacements_arr;
    }
    public ArrayList<Company> getcompany_arr(){
        return company_arr;
    }
    public void show_details(int roll){
        for(int i=0;i<students_arr.size();i++){
            if(students_arr.get(i).getroll()==roll){
                students_arr.get(i).show_student_details();
            }
        }
    }
    public void getcompanydetail(String name){
        for(int i=0;i<company_arr.size();i++){
            if(company_arr.get(i).getcompany_name().equals(name)){
                company_arr.get(i).show_company_details();
            }
        }
    }
    public void select_students(String company){
        System.out.println("Roll Number of selected Students : ");
        int[][] techincalscores;
        int total_students;
        int number_of_required_students;
        int max=-1;
        int count;
        int test=-2;
        int test1=-2;
        boolean jkl=true;
        boolean ghj=true;
        float maxgpa;
        int select=-1;
        ArrayList<Student> scores = new ArrayList<Student>();
        for(int k=0;k<company_arr.size();k++){
            if(company_arr.get(k).getcompany_name().equals(company)){
                techincalscores=company_arr.get(k).gettechnicalscores();
                number_of_required_students=company_arr.get(k).getnumber_of_required_students();
                total_students=company_arr.get(k).gettotal_students();
                while(number_of_required_students>0){
                    max=-1;
                    test=-2;
                    test1=-2;
                    ghj=true;
                    jkl=true;
                    select=0;
                    maxgpa=0;
                    count=0;
                    for(int j=0;j<total_students;j++){
                        if(techincalscores[1][j]!=0){
                            if(max<techincalscores[1][j]){
                                max=techincalscores[1][j];
                                select=j+1;
                            }
                        }
                    }
                    for(int j=0;j<total_students;j++){
                        if(techincalscores[1][j]==max){
                            count++;
                        }
                    }
                    if(count==1){
                        System.out.println(select);
                        techincalscores[1][select-1]=0;
                        for(int i=0;i<ongoingplacements_arr.size();i++){
                            if(ongoingplacements_arr.get(i).getroll()==select && jkl==true){
                                placedstudents_arr.add(ongoingplacements_arr.get(i));
                                test1=i;
                                jkl=false;
                                break;
                            }
                        }
                        ongoingplacements_arr.remove(test1);
                    }
                    else{
                        for(int i=0;i<total_students;i++){
                            if(techincalscores[1][i]==max){
                                for(int j=0;j<ongoingplacements_arr.size();j++){
                                    if(ongoingplacements_arr.get(j).getroll()==i+1){
                                        maxgpa=Math.max(maxgpa,ongoingplacements_arr.get(j).getgpa());
                                    }
                                }
                            }
                        }
                        for(int i=0;i<ongoingplacements_arr.size();i++){
                            if(ongoingplacements_arr.get(i).getgpa()==maxgpa){
                                System.out.println(ongoingplacements_arr.get(i).getroll());
                                select=ongoingplacements_arr.get(i).getroll()+1;
                            }
                            for(int l=0;l<ongoingplacements_arr.size();l++){
                                if(ongoingplacements_arr.get(l).getroll()==select && ghj==true){
                                    placedstudents_arr.add(ongoingplacements_arr.get(l));
                                    ghj=false;
                                    test=l;
                                    break;
                                }
                            }
                        }
                        ongoingplacements_arr.remove(test);
                    }
                    number_of_required_students--;
                }
                for(int g=0;g<company_arr.size();g++){
                    if(company_arr.get(g).getcompany_name().equals(company)){
                        company_arr.get(g).setapplicationstatus();
                    }
                }
            }
        }
    }
    public void check_technical_result_all(int roll){
        int[][] techincalscores;
        boolean asd=true;
        for(int i=0;i<placedstudents_arr.size();i++){
            if(placedstudents_arr.get(i).getroll()==roll){
                asd=false;
            }
        }
        if(asd){
            for(int i=0;i<company_arr.size();i++){
                techincalscores=company_arr.get(i).gettechnicalscores();
                if(techincalscores[1][roll-1]!=0){
                    System.out.println(company_arr.get(i).getcompany_name()+" "+techincalscores[1][roll-1]);
                }
            }
        }
        else{
            System.out.println("No student with the given roll no has account");
        }
    }
    public void check_qualified_Students(Company comp){
        int[][] techincalscores;
        Scanner abc = new Scanner(System.in);
        for(int i=0;i<students_arr.size();i++){
            for(int j=0;j<comp.geteligiblecourses_arr().size();j++){
                if(students_arr.get(i).getbranch().equals(comp.geteligiblecourses_arr().get(j))){
                    System.out.print("Enter the score for Roll no. "+students_arr.get(i).getroll()+" ");
                    techincalscores=comp.gettechnicalscores();
                    techincalscores[1][students_arr.get(i).getroll()-1]=abc.nextInt();
                }
            }
        }
    }
    public void check_open_companies(){
            for(int i=0;i<company_arr.size();i++){
                if(company_arr.get(i).getapplication_status().equals("OPEN")){
                System.out.println(company_arr.get(i).getcompany_name());
                }
            }
        }
    public void all_removed(){
        System.out.println("Accounts removed for :");
        for(int i=0;i<placedstudents_arr.size();i++){
            System.out.println(placedstudents_arr.get(i).getroll());
        }
    }
    public void all_companyclosed(){
        int test=-2;
        System.out.println("Accounts removed for :");
        for(int i=0;i<company_arr.size();i++){
            if(company_arr.get(i).getapplication_status().equals("CLOSED")){
                System.out.println(company_arr.get(i).getcompany_name());
                test=i;
            }
        }
        company_arr.remove(test);
        System.out.println(company_arr.size());
        // Iterator<Company> itr = company_arr.iterator();

        // while(itr.hasNext()){
        //     Company co = itr.next();
        //     if(co.getapplication_status().equals("CLOSED")){
        //         company_arr.remove(co);
        //     }
        // }

    }
}
public class LA1{
    public static void main(String[] args) {
    
       Scanner scan = new Scanner(System.in);
       int number_of_students=scan.nextInt();
       Placement_Office ploff = new Placement_Office();
       String company_name;
       int number_of_eligible_courses;
       ArrayList<String> eligiblecourses_arr = new ArrayList<String>();
        int number_of_required_students;

       for(int i=0;i<number_of_students;i++){
            Student std = new Student(i+1,scan.nextFloat(),scan.next(),"Not Placed");
            ploff.add_student(std);
       }

       System.out.println("----students registered----");
       
       while(ploff.getongoingplacements_arr().size()>0 || ploff.getcompany_arr().size()>0){
            switch(scan.nextInt()){
                case 1:
                    company_name=scan.next();
                    System.out.print("Number of eligible courses : ");
                    number_of_eligible_courses=scan.nextInt();
                    for(int i=0;i<number_of_eligible_courses;i++){
                        eligiblecourses_arr.add(scan.next());
                    }
                    System.out.print("Number of required students : ");
                    number_of_required_students=scan.nextInt();
                    Company comp = new Company(company_name,number_of_eligible_courses,eligiblecourses_arr,number_of_required_students,number_of_students);
                    ploff.add_company(comp);
                    
                    comp.show_company_details();
                    
                    System.out.println("Enter the score for technical rounds");
                    ploff.check_qualified_Students(comp);
                    break;
                    
                case 2:
                    ploff.all_removed();
                    break;

                case 3:
                    ploff.all_companyclosed();
                    break;

                case 4:
                    System.out.println(ploff.getongoingplacements_arr().size()+" students left.");
                    break;

                case 5:
                    ploff.check_open_companies();
                    break;
                
                case 6:
                    ploff.select_students(scan.next());

                    break;

                case 7:
                    ploff.getcompanydetail(scan.next());
                    break;    

                case 8:
                    ploff.show_details(scan.nextInt()); 
                    break;

                case 9:
                    ploff.check_technical_result_all(scan.nextInt());
                    break;

                default:
                    System.out.println("Inavlid option, please try again.");
                    break;
            }
       }
    }
}


