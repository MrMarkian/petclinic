package com.springframework.petclinic.bootstrap;

import com.springframework.petclinic.model.*;
import com.springframework.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    private final AccountService accountService;

    private final PaymentService paymentService;

    private final InvoiceService invoiceService;


    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService, AccountService accountService, PaymentService paymentService, InvoiceService invoiceService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
        this.accountService = accountService;
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
    }

    @Override
    public void run(String... args) {

        int count = petTypeService.findAll().size();

        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        PetType rabbit = new PetType();
        rabbit.setName("Rabbit");
        PetType savedRabbitPetType = petTypeService.save(rabbit);

        PetType Hamster = new PetType();
        Hamster.setName("Hamster");
        PetType savedHamsterPetType = petTypeService.save(Hamster);
        
        PetType lizard = new PetType();
        lizard.setName("Lizard");
        PetType savedlizardPetType = petTypeService.save(lizard);

        PetType guinea_pig = new PetType();
        guinea_pig.setName("Guinea Pig");
        PetType savedGuinea_pigPetType = petTypeService.save(guinea_pig);

        PetType degus = new PetType();
        degus.setName("Degu");
        PetType savedDegusPetType = petTypeService.save(degus);

        PetType Chinchilla = new PetType();
        Chinchilla.setName("Chinchilla");
        PetType savedChichillaPetType = petTypeService.save(Chinchilla);




        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);



        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1231231234");
        owner1.setPostCode("MI2 7MI");


        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);


        Account mikesAccount = new Account();
        Payment payment1 = new Payment();

        Invoice testInvoice = new Invoice();

        testInvoice.setTotal(15F);
        testInvoice.setAccount(mikesAccount);
        Invoice testInvoice2 = new Invoice();

        testInvoice2.setTotal(55F);

        testInvoice2.setAccount(mikesAccount);
        mikesAccount.getInvoiceList().add(testInvoice);
        mikesAccount.getInvoiceList().add(testInvoice2);


        mikesAccount.setOwner(owner1);
        mikesAccount.setPaymentType("Invoice Account");
        owner1.getAccounts().add(mikesAccount);
        Account mikesCreditAccount = new Account();
        mikesCreditAccount.setOwner(owner1);
        mikesCreditAccount.setActive(true);
        mikesCreditAccount.setPaymentType("Credit Account");
        owner1.getAccounts().add(mikesCreditAccount);

        payment1.setAccount(mikesAccount);
        payment1.setInputDate(LocalDate.now());
        payment1.setAmount(199.99F);
        payment1.setPaymentDate(LocalDate.now());
        payment1.setComment("First Test Payment !");



        ownerService.save(owner1);
        accountService.save(mikesAccount);
        paymentService.save(payment1);
        invoiceService.save(testInvoice);
        invoiceService.save(testInvoice2);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("38 Penny Lane");
        owner2.setCity("Michigan");
        owner2.setTelephone("1231231234");
        owner2.setPostCode("MC138M");


        Pet fionasCat = new Pet();
        fionasCat.setName("Boris");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Faye");
        owner3.setLastName("Glevier");
        owner3.setAddress("69 Red Light Lane");
        owner3.setCity("Seattle");
        owner3.setTelephone("555-999-888");
        owner3.setPostCode("SE13 8L");


        Pet faysCat = new Pet();
        faysCat.setName("Xena");
        faysCat.setOwner(owner3);
        faysCat.setBirthDate(LocalDate.now());
        faysCat.setPetType(savedCatPetType);
        owner3.getPets().add(faysCat);

        ownerService.save(owner3);


        Visit catVisit = new Visit();
        catVisit.setPet(fionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");

        visitService.save(catVisit);


        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.setAddress("123 Bakersville Street");
        vet1.setCity("London");
        vet1.setTelephone("0112939377");
        vet1.getSpecialitySet().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.setAddress("99 Ballon Street");
        vet2.setCity("Sussex");
        vet2.setTelephone("0227336633");
        vet2.getSpecialitySet().add(savedSurgery);
        vet2.getSpecialitySet().add(savedDentistry);
        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Jackie");
        vet3.setLastName("Parker");
        vet3.getSpecialitySet().add(savedSurgery);
        vet3.getSpecialitySet().add(savedDentistry);
        vetService.save(vet3);

        System.out.println("Loaded Vets....");
        System.out.println(vet2.getFirstName());

        System.out.println("--- DEBUG ---");

        catVisit.setVet(vet1);
        vet1.getVisitSet().add(catVisit);

        vetService.save(vet1);
        visitService.save(catVisit);

        System.out.println(mikesAccount.getBalance());
        System.out.println(mikesAccount.getPaymentTotal());
    }
}
