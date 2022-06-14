package DatabaseWorkers;

import classesandenums.*;
import exceptions.DatabaseHandlingException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

/**
 * Operates the database collection itself.
 */
public class DataBaseCollectionManager {
    // PERSONS_TABLE
    private final String SELECT_ALL_PERSONS = "SELECT * FROM " + DataBaseHandler.PERSONS_TABLE;
    private final String SELECT_PERSONS_BY_ID = SELECT_ALL_PERSONS + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String SELECT_PERSONS_BY_ID_AND_USER_ID = SELECT_PERSONS_BY_ID + " AND " +
            DataBaseHandler.PERSONS_TABLE_USER_ID_COLUMN + " = ?";
    private final String INSERT_PERSONS = "INSERT INTO " +
            DataBaseHandler.PERSONS_TABLE + " (" +
            DataBaseHandler.PERSONS_TABLE_NAME_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_COORDINATES_X_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_COORDINATES_Y_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_HEIGHT_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_CREATION_DATE_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_EYE_COLOR_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_HAIR_COLOR_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_NATIONALITY_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_LOCATION_X_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_LOCATION_Y_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_LOCATION_Z_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_LOCATION_NAME_COLUMN + ", " +
            DataBaseHandler.PERSONS_TABLE_USER_ID_COLUMN + ") VALUES (" +
//            "?," +
            " ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String DELETE_PERSONS_BY_ID = "DELETE FROM " + DataBaseHandler.PERSONS_TABLE +
            " WHERE " + DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_NAME_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_NAME_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_CREATION_DATE_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_CREATION_DATE_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_COORDINATES_X_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_COORDINATES_X_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_COORDINATES_Y_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_COORDINATES_Y_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_HEIGHT_COLUMN_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_HEIGHT_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_EYE_COLOR_COLUMN_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_EYE_COLOR_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_HAIR_COLOR_COLUMN_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_HAIR_COLOR_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_NATIONALITY_COLUMN_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_NATIONALITY_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_LOCATION_X_COLUMN_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_LOCATION_X_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_LOCATION_Y_COLUMN_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_LOCATION_Y_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_LOCATION_Z_COLUMN_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_LOCATION_Z_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final String UPDATE_PERSONS_LOCATION_NAME_COLUMN_BY_ID = "UPDATE " + DataBaseHandler.PERSONS_TABLE + " SET " +
            DataBaseHandler.PERSONS_TABLE_LOCATION_NAME_COLUMN + " = ?" + " WHERE " +
            DataBaseHandler.PERSONS_TABLE_ID_COLUMN + " = ?";
    private final DataBaseHandler databaseHandler;
    private final DataBaseUserManager databaseUserManager;

    public DataBaseCollectionManager(DataBaseHandler databaseHandler, DataBaseUserManager databaseUserManager) {
        this.databaseHandler = databaseHandler;
        this.databaseUserManager = databaseUserManager;
    }

    /**
     * Create Person.
     *
     * @param resultSet Result set parameters of Marine.
     * @return New Marine.
     */
    private Person createPerson(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(DataBaseHandler.PERSONS_TABLE_ID_COLUMN);
            String name = resultSet.getString(DataBaseHandler.PERSONS_TABLE_NAME_COLUMN);
            Coordinates coordinates = new Coordinates(resultSet.getDouble(DataBaseHandler.PERSONS_TABLE_COORDINATES_X_COLUMN), resultSet.getLong(DataBaseHandler.PERSONS_TABLE_COORDINATES_Y_COLUMN));
            int height = resultSet.getInt(DataBaseHandler.PERSONS_TABLE_HEIGHT_COLUMN);
            LocalDateTime creationDate = LocalDateTime.parse(resultSet.getString(DataBaseHandler.PERSONS_TABLE_CREATION_DATE_COLUMN));
            EColor eyeColor = EColor.valueOf(resultSet.getString(DataBaseHandler.PERSONS_TABLE_EYE_COLOR_COLUMN));
            HColor hairColor = HColor.valueOf(resultSet.getString(DataBaseHandler.PERSONS_TABLE_HAIR_COLOR_COLUMN));
            Country country = Country.valueOf(resultSet.getString(DataBaseHandler.PERSONS_TABLE_NATIONALITY_COLUMN));
            Location location = new Location(resultSet.getDouble(DataBaseHandler.PERSONS_TABLE_LOCATION_X_COLUMN),
                    resultSet.getDouble(DataBaseHandler.PERSONS_TABLE_LOCATION_Y_COLUMN),
                    resultSet.getLong(DataBaseHandler.PERSONS_TABLE_LOCATION_Z_COLUMN),
                    resultSet.getString(DataBaseHandler.PERSONS_TABLE_LOCATION_NAME_COLUMN));
            User owner = databaseUserManager.getUserById(resultSet.getLong(DataBaseHandler.PERSONS_TABLE_USER_ID_COLUMN));
            return new Person(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    height,
                    eyeColor,
                    hairColor,
                    country,
                    location,
                    owner
            );
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * @return List of Persons.
     */
    public LinkedHashSet<Person> getCollection() {
        LinkedHashSet<Person> PersonCollection = new LinkedHashSet<>();
        PreparedStatement preparedSelectAllStatement = null;
        try {
            preparedSelectAllStatement = databaseHandler.getPreparedStatement(SELECT_ALL_PERSONS, false);
            ResultSet resultSet = preparedSelectAllStatement.executeQuery();
            while (resultSet.next()) {
                PersonCollection.add(createPerson(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectAllStatement);
        }
        return PersonCollection;
    }

    /**
     * @param newPerson Person raw.
     * @param user      User.
     * @return Person.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public Person insertPERSONS(Person newPerson, User user) throws DatabaseHandlingException {
        PreparedStatement preparedInsertPERSONStatement = null;
        try {
            databaseHandler.setCommitMode();
            databaseHandler.setSavepoint();
            preparedInsertPERSONStatement = databaseHandler.getPreparedStatement(INSERT_PERSONS, true);
            preparedInsertPERSONStatement.setString(1, newPerson.getName());
            preparedInsertPERSONStatement.setDouble(2, newPerson.getCoordinates().getX());
            preparedInsertPERSONStatement.setLong(3, newPerson.getCoordinates().getY());
            preparedInsertPERSONStatement.setDouble(4, newPerson.getHeight());
            preparedInsertPERSONStatement.setString(5, newPerson.getCreationDate().toString());
            preparedInsertPERSONStatement.setString(6, String.valueOf(newPerson.getEyeColor()));
            preparedInsertPERSONStatement.setString(7, String.valueOf(newPerson.getHairColor()));
            preparedInsertPERSONStatement.setString(8, String.valueOf(newPerson.getNationality()));
            preparedInsertPERSONStatement.setDouble(9, newPerson.getLocation().getX());
            preparedInsertPERSONStatement.setDouble(10, newPerson.getLocation().getY());
            preparedInsertPERSONStatement.setDouble(11, newPerson.getLocation().getZ());
            preparedInsertPERSONStatement.setString(12, newPerson.getLocation().getName());
            preparedInsertPERSONStatement.setLong(13, databaseUserManager.getUserIdByUsername(user));
            if (preparedInsertPERSONStatement.executeUpdate() == 0) throw new SQLException();
            ResultSet generatedPersonKeys = preparedInsertPERSONStatement.getGeneratedKeys();//???
            long personID;
            if (generatedPersonKeys.next()) {
                personID = generatedPersonKeys.getLong(1);
            } else throw new SQLException();
            databaseHandler.commit();
            newPerson.setOwner(user);
            newPerson.setId(personID);
            return newPerson;
        } catch (SQLException exception) {
            exception.printStackTrace();
            databaseHandler.rollback();
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedInsertPERSONStatement);
            databaseHandler.setNormalMode();
        }
    }

    /**
     * @param newPerson Person raw.
     * @param personID  ID of PERSONS.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public void updatePERSONSById(long personID, Person newPerson) throws DatabaseHandlingException {
        PreparedStatement preparedUpdatePERSONNameByIdStatement = null;
        PreparedStatement preparedUpdatePERSONCreationDateByIdStatement = null;
        PreparedStatement preparedUpdatePERSONCoordinatesXByIdStatement = null;
        PreparedStatement preparedUpdatePERSONCoordinatesYByIdStatement = null;
        PreparedStatement preparedUpdatePERSONHeightByIdStatement = null;
        PreparedStatement preparedUpdatePERSONEyeColorByIdStatement = null;
        PreparedStatement preparedUpdatePERSONHairColorByIdStatement = null;
        PreparedStatement preparedUpdatePERSONNationalityByIdStatement = null;
        PreparedStatement preparedUpdatePERSONLocationXByIdStatement = null;
        PreparedStatement preparedUpdatePERSONLocationYByIdStatement = null;
        PreparedStatement preparedUpdatePERSONLocationZByIdStatement = null;
        PreparedStatement preparedUpdatePERSONLocationNameByIdStatement = null;
        try {
            databaseHandler.setCommitMode();
            databaseHandler.setSavepoint();

            preparedUpdatePERSONNameByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_NAME_BY_ID, false);
            preparedUpdatePERSONCreationDateByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_CREATION_DATE_BY_ID, false);
            preparedUpdatePERSONCoordinatesXByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_COORDINATES_X_BY_ID, false);
            preparedUpdatePERSONCoordinatesYByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_COORDINATES_Y_BY_ID, false);
            preparedUpdatePERSONHeightByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_HEIGHT_COLUMN_BY_ID, false);
            preparedUpdatePERSONEyeColorByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_EYE_COLOR_COLUMN_BY_ID, false);
            preparedUpdatePERSONHairColorByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_HAIR_COLOR_COLUMN_BY_ID, false);
            preparedUpdatePERSONNationalityByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_NATIONALITY_COLUMN_BY_ID, false);
            preparedUpdatePERSONLocationXByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_LOCATION_X_COLUMN_BY_ID, false);
            preparedUpdatePERSONLocationYByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_LOCATION_Y_COLUMN_BY_ID, false);
            preparedUpdatePERSONLocationZByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_LOCATION_Z_COLUMN_BY_ID, false);
            preparedUpdatePERSONLocationNameByIdStatement = databaseHandler.getPreparedStatement(UPDATE_PERSONS_LOCATION_NAME_COLUMN_BY_ID, false);
            if (newPerson.getName() != null) {
                preparedUpdatePERSONNameByIdStatement.setString(1, newPerson.getName());
                preparedUpdatePERSONNameByIdStatement.setLong(2, personID);
                if (preparedUpdatePERSONNameByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newPerson.getCreationDate() != null) {
                preparedUpdatePERSONCreationDateByIdStatement.setString(1, newPerson.getCreationDate().toString());
                preparedUpdatePERSONCreationDateByIdStatement.setLong(2, personID);
                if (preparedUpdatePERSONCreationDateByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newPerson.getCoordinates() != null) {
                preparedUpdatePERSONCoordinatesXByIdStatement.setDouble(1, newPerson.getCoordinates().getX());
                preparedUpdatePERSONCoordinatesXByIdStatement.setLong(2, personID);
                preparedUpdatePERSONCoordinatesYByIdStatement.setLong(1, newPerson.getCoordinates().getY());
                preparedUpdatePERSONCoordinatesYByIdStatement.setLong(2, personID);
                if (preparedUpdatePERSONCoordinatesXByIdStatement.executeUpdate() == 0 || preparedUpdatePERSONCoordinatesYByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
            }
            if (newPerson.getHeight() > 0) {
                preparedUpdatePERSONHeightByIdStatement.setInt(1, newPerson.getHeight());
                preparedUpdatePERSONHeightByIdStatement.setLong(2, personID);
                if (preparedUpdatePERSONHeightByIdStatement.executeUpdate() == 0) throw new SQLException();
            }
            if (newPerson.getEyeColor() != null) {
                preparedUpdatePERSONEyeColorByIdStatement.setString(1, String.valueOf(newPerson.getEyeColor()));
                preparedUpdatePERSONEyeColorByIdStatement.setLong(2, personID);
                if (preparedUpdatePERSONEyeColorByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
            }
            if (newPerson.getHairColor() != null) {
                preparedUpdatePERSONHeightByIdStatement.setString(1, String.valueOf(newPerson.getHairColor()));
                preparedUpdatePERSONHeightByIdStatement.setLong(2, personID);
                if (preparedUpdatePERSONHairColorByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
            }
            if (newPerson.getNationality() != null) {
                preparedUpdatePERSONNationalityByIdStatement.setString(1, String.valueOf(newPerson.getNationality()));
                preparedUpdatePERSONNationalityByIdStatement.setLong(2, personID);
                if (preparedUpdatePERSONNationalityByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
            }
            if (newPerson.getLocation() != null) {
                if (newPerson.getLocation().getX() != null) {
                    preparedUpdatePERSONLocationXByIdStatement.setDouble(1, newPerson.getLocation().getX());
                    preparedUpdatePERSONLocationXByIdStatement.setLong(2, personID);
                    if (preparedUpdatePERSONLocationXByIdStatement.executeUpdate() == 0)
                        throw new SQLException();
                }
                preparedUpdatePERSONLocationYByIdStatement.setDouble(1, newPerson.getLocation().getY());
                preparedUpdatePERSONLocationYByIdStatement.setLong(2, personID);
                if (preparedUpdatePERSONLocationYByIdStatement.executeUpdate() == 0)
                    throw new SQLException();
                if (newPerson.getLocation().getZ() != null) {
                    preparedUpdatePERSONLocationZByIdStatement.setDouble(1, newPerson.getLocation().getZ());
                    preparedUpdatePERSONLocationZByIdStatement.setLong(2, personID);
                    if (preparedUpdatePERSONLocationZByIdStatement.executeUpdate() == 0)
                        throw new SQLException();
                }
                if (newPerson.getLocation().getName() != null) {
                    preparedUpdatePERSONLocationNameByIdStatement.setString(1, newPerson.getLocation().getName());
                    preparedUpdatePERSONLocationNameByIdStatement.setLong(2, personID);
                    if (preparedUpdatePERSONLocationNameByIdStatement.executeUpdate() == 0)
                        throw new SQLException();
                }
            }
            databaseHandler.commit();
        } catch (SQLException exception) {
            databaseHandler.rollback();
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedUpdatePERSONNameByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONCreationDateByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONCoordinatesXByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONCoordinatesYByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONHeightByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONEyeColorByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONHairColorByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONNationalityByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONLocationXByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONLocationYByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONLocationZByIdStatement);
            databaseHandler.closePreparedStatement(preparedUpdatePERSONLocationNameByIdStatement);
            databaseHandler.setNormalMode();
        }

    }

    /**
     * Delete Person by id.
     *
     * @param personID ID of PERSON.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public void deletePERSONSById(long personID) throws DatabaseHandlingException {
        PreparedStatement preparedDeletePERSONSByIdStatement = null;
        try {
            preparedDeletePERSONSByIdStatement = databaseHandler.getPreparedStatement(DELETE_PERSONS_BY_ID, false);
            preparedDeletePERSONSByIdStatement.setLong(1, personID);
            if (preparedDeletePERSONSByIdStatement.executeUpdate() == 0) throw new DatabaseHandlingException();
        } catch (SQLException exception) {
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedDeletePERSONSByIdStatement);
        }
    }

    /**
     * Checks Person user id.
     *
     * @param PersonId ID of person.
     * @param user     Owner of person.
     * @return Is everything ok.
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public boolean checkPERSONSUserId(long PersonId, User user) throws DatabaseHandlingException {
        PreparedStatement preparedSelectPERSONSByIdAndUserIdStatement = null;
        try {
            preparedSelectPERSONSByIdAndUserIdStatement = databaseHandler.getPreparedStatement(SELECT_PERSONS_BY_ID_AND_USER_ID, false);
            preparedSelectPERSONSByIdAndUserIdStatement.setLong(1, PersonId);
            preparedSelectPERSONSByIdAndUserIdStatement.setLong(2, databaseUserManager.getUserIdByUsername(user));
            ResultSet resultSet = preparedSelectPERSONSByIdAndUserIdStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            throw new DatabaseHandlingException();
        } finally {
            databaseHandler.closePreparedStatement(preparedSelectPERSONSByIdAndUserIdStatement);
        }
    }

    /**
     * Clear the collection.
     *
     * @throws DatabaseHandlingException When there's exception inside.
     */
    public void clearCollection() throws DatabaseHandlingException {
        LinkedHashSet<Person> PersonsCollection = getCollection();
        for (Person p : PersonsCollection) {
            deletePERSONSById(p.getId());
        }
    }
}
