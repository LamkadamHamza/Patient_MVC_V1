package ma.enset.Patient_MVC.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.Patient_MVC.entities.Patient;
import ma.enset.Patient_MVC.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientsController {
    private PatientRepository patientRepository;

    @GetMapping(path = "/user/index")
    @PreAuthorize("hasRole('USER')")
    public  String patients(Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page ,
                            @RequestParam(name = "size", defaultValue = "4")   int size,
                            @RequestParam(name = "keyword", defaultValue = "")   String key ){
        Page<Patient> patients =patientRepository.findByNomContains(key,PageRequest.of(page,size));
        model.addAttribute("Listpatients", patients.getContent());
        model.addAttribute("pages",new int[patients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword", key);
        return "patients";
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public  String home(){
        return  "redirect:/user/index";
    }

    @GetMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(Long id,String keyword,int page)
    {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }


    @GetMapping (path = "/admin/editPatient")
    @PreAuthorize("hasRole('ADMIN')")
    public  String editePatient(Model model ,@RequestParam(name = "id") Long id){

        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient==null) throw  new RuntimeException("Patient n'existe pas ");
        model.addAttribute("patient",patient);
        return "editPatient";
    }
    @GetMapping("/admin/FormPatient")
    @PreAuthorize("hasRole('ADMIN')")
    public String formPatient(Model model ){
        model.addAttribute("patient",new Patient());
        return "FormPatient";
    }
    @PostMapping("/admin/savePatient")
    @PreAuthorize("hasRole('ADMIN')")
    public String save(@Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "FormPatient";
        }
         patientRepository.save(patient);
        return "redirect:/index?keyword="+patient.getNom();
    }
}
