package hh.swd20.contactdetails.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.contactdetails.domain.ContactType;
import hh.swd20.contactdetails.domain.ContactTypeRepository;

@Controller
public class ContactTypeController {

	@Autowired
	private ContactTypeRepository crepository;
	
	// yhteystyyppien listaus
	@RequestMapping(value="/contacttypelist", method=RequestMethod.GET)
	public String getAllContactTypes(Model model) {
		List<ContactType> contactTypes = (List<ContactType>) crepository.findAll();
		model.addAttribute("contactTypes", contactTypes);
		return "contacttypelist";
	}
	
	// yhteystyypin lisäys
	// tyhjä yhteystyyppilomakkeen muodostus
	@RequestMapping(value="/addcontacttype", method=RequestMethod.GET)
	public String addContactType(Model model) {
		model.addAttribute("contactType", new ContactType());
		return "addcontacttype";
	}
	
	// yhteystyyppilomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value="/savecontacttype", method=RequestMethod.POST)
	public String saveContactType(@ModelAttribute ContactType contactType) {
		crepository.save(contactType);
		return "redirect:/contacttypelist";
	}
	
	// yhteystyypin poisto id:n avulla
	@PreAuthorize("hasAuthority('USER')")
	@RequestMapping(value="/deletecontact/{id}", method=RequestMethod.GET)
	public String deleteContactType(@PathVariable("id")Long contactId, Model model) {
		crepository.deleteById(contactId);
		return "redirect:../contacttypelist";
	}
	
}
