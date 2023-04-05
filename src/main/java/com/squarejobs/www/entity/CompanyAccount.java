package com.squarejobs.www.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity //  используется для пометки класса как объекта, который должен быть сохранен в базе данных. Если не указать имя, то сущность будет называться CompanyAccount
@Table(
        name = "company_account"  // имя таблицы, которая будет использоваться для хранения объектов этого класса в базе данных
)
public class CompanyAccount { // т.е. таблица company_account имеет представителся класс CompanyAccount
    @Id
    @GeneratedValue( // указывает способ создания главного ключа
            strategy = GenerationType.SEQUENCE, // strategy указывает на то, каким образом будут генерироваться значения для первичного ключа
            generator = "companyAccount_sequence" // companyAccount_sequence - это sequence объект базы данных, которая генерирует уникальные идентификаторы для записей в таблице
    )
    @SequenceGenerator( // используется для создания и настройки объекта SequenceGenerator
            name = "companyAccount_sequence",
            sequenceName = "companyAccount_sequence",
            allocationSize = 1
    )
    @Column(
            name = "pk_account",
            updatable = false
    )
    private Long pkAccount;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;
    @Column(name = "email_status")
    private int emailStatus;
    //@Lob
    @Column(name = "photo")
    private byte[] photo;
    @OneToOne(fetch = FetchType.LAZY) /**(cascade= CascadeType.ALL)*/
    @JoinColumn(name = "pk_company") // -> указываем primary key для таблицы с которой связываем
    private Company company;


    public CompanyAccount(String email, String password, int emailStatus, byte[] photo, Company company) {
        this.email = email;
        this.password = password;
        this.emailStatus = emailStatus;
        this.photo = photo;
        this.company = company;
    }

    public CompanyAccount() {
    }
}

/** Mapping класса CompanyAccount означает, что мы связываем класс CompanyAccount с таблицей account_company в БД.
 * Мэппинг (от англ. mapping) класса в Hibernate - это процесс связывания класса Java с таблицей в базе данных.
 * Когда мы создаем класс с аннотацией @Entity и полями, также аннотированными соответствующим образом, мы говорим Hibernate, что этот класс представляет таблицу в базе данных.
 * Для связывания полей класса с полями таблицы используются различные аннотации Hibernate.
 *
 * ! Для связывания класса с таблицей мы указываем аннотацию @Entity, а для связывания полей класса с полями таблицы мы используем аннотации @Id, @Column и т.д.
 *
 * @Entity
 *  Класс, помеченный аннотацией @Entity, должен иметь конструктор по умолчанию и набор геттеров и сеттеров для всех свойств.
 *  Также необходимо указать имя таблицы, которая будет использоваться для хранения объектов этого класса в базе данных, с помощью аннотации @Table.
 *  То есть мы имеем в БД структуру таблицы с названием "table", а @Entity говорит что созданные обекты этой талицы будут помещаться в БД.
 *
 * @Table @Entity параметр name
 * Если в аннотации @Table не указывать параметр name, то Hibernate будет использовать имя класса, который аннотирован @Entity, для создания таблицы.
 * В случае, когда класс имеет имя CompanyAccount, то по умолчанию название таблицы будет также CompanyAccount.
 * Если и в аннотации @Entity и в аннотации @Table задан параметр name, то название таблицы будет взято из аннотации @Table.
 *
 @GeneratedValue
 * в данном примере, при создании нового объекта, Hibernate будет использовать
 * SEQUENCE для генерации уникальных значений для первичного ключа сущности, используя генератор идентификаторов с именем companyAccount_sequence.
 *
 * strategy = GenerationType.SEQUENCE означает, что Hibernate будет использовать базу данных для генерации уникальных идентификаторов для каждой новой сущности.
 * Для этого Hibernate будет использовать объекты sequence базы данных.
 * Sequence - это объект базы данных, который генерирует уникальные значения.
 * Это часто используется для генерации значений первичных ключей, так как это обеспечивает уникальность значений в рамках всей базы данных.
 *
 * generator - это sequence объект базы данных, которая генерирует уникальные идентификаторы для записей в таблице.
 * При использовании такой последовательности, каждый раз при вставке новой записи в таблицу с данными о компании,
 * она будет получать новый идентификатор, значение которого будет увеличиваться на единицу относительно предыдущей записи.
 * Таким образом, использование последовательности обеспечивает уникальность идентификаторов в таблице и предотвращает коллизии
 * (ситуации, когда две или более записи имеют одинаковый идентификатор).
 *
 * @SequenceGenerator
 * Аннотация @SequenceGenerator используется для создания и настройки объекта SequenceGenerator
 *
 * В строке name = "companyAccount_sequence" задается имя генератора идентификаторов, которое будет использоваться для ссылки на объект SequenceGenerator в других местах кода.
 * В строке sequenceName = "companyAccount_sequence" указывается имя последовательности в базе данных, которая будет использоваться для генерации идентификаторов.
 * В строке allocationSize = 1 определяется количество идентификаторов, которое будет выделяться за один запрос к последовательности.
 * В данном случае, установлено значение 1, что означает, что каждый раз будет запрашиваться только одно значение из последовательности.
 *
 * Создание Таблицы
 * Не обязательно сначала создавать 'каркас таблицы' в БД самостоятельно.
 * Существуют 1) автоматическая генерация таблиц при каждом запуске приложения, 2) генерация таблиц при наличии определенного свойства в файле конфигурации, или 3) создание таблиц вручную.
 * 1) наиболее распространенные стратегии создания таблиц : create, create-drop.
 *    Стратегия create позволяет Hibernate создавать таблицы при старте приложения, при этом если таблица уже существует, Hibernate удалит ее и создаст новую.
 *    Стратегия create-drop работает аналогично, но таблица будет удалена при остановке приложения.
 *    Эту строку нудно добавить в hibernate.cfg.xml
 *
 * @@OneToOne
 * - @OneToOne(mappedBy = "companyAccount") - ставится в классе где нет foreign key; Это означает, что связь один-к-одному на самом деле находится в сущности Company, где и есть
 *  foreign key = CompanyAccount companyAccount, и ссылка на объект CompanyAccount содержится в поле companyAccount
 * - @OneToOne
 *   @JoinColumn(name = "pk_account") - ставится над foreign key. Вместо   private Integer fkAccount в Company -> CompanyAccount companyAccount.
 *   Аннотация @JoinColumn указывает, какую колонку в таблице базы данных следует использовать для связи с объектом Person
 */
