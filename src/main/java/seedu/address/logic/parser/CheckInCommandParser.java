package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUEST;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.CheckInCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.List;

public class CheckInCommandParser implements Parser<CheckInCommand> {

    @Override
    public CheckInCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GUEST);

        Index roomIndex;
        try {
            roomIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CheckInCommand.MESSAGE_USAGE), ive);
        }

        List<String> guestList = argMultimap.getAllValues(PREFIX_GUEST);
        List<Index> guestIndexes = new ArrayList<>();
        try {
            for (String guestString : guestList) {
                guestIndexes.add(ParserUtil.parseIndex(guestString));
            }
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CheckInCommand.MESSAGE_USAGE), ive);
        }

        return new CheckInCommand(roomIndex, guestIndexes);
    }
}
