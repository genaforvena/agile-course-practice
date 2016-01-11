package ru.unn.agile.Minesweeper.viewmodel;

import ru.unn.agile.Minesweeper.Model.Model;

import java.util.List;

public class ViewModel {

    private final Model minesweeperModel = new Model();

    private ILogger logger;
    private String[] fullLog = new String[0];

    private enum CellText {
        flag("!"),
        close("#"),
        issue("?"),
        mine("*");

        private final String text;

        private CellText(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    private enum SmilesText {
        smile("Живой"),
        dead("Мертвый"),
        winner("Выиграл");

        private final String text;

        private SmilesText(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public String[] getFullLog() {
        return fullLog;
    }

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger parameter can't be null");
        }
        this.logger = logger;
    }
    private void updateLogs() {
        List<String> logList = logger.getLog();
        fullLog = new String[logList.size()];
        for (int i = 0; i < logList.size(); i++) {
            fullLog[i] = logList.get(i);
        }
    }

    public ViewModel() {
        /* empty */
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        /* empty */
    }

    public void newGame() {
        minesweeperModel.newGame();
        logger.log(LogMessages.NEW_GAME_WAS_PRESSED);
        updateLogs();
    }

    public void openCell(final int x, final int y) {
        if (minesweeperModel.openCell(x, y)) {
            logger.log(LogMessages.openCell(x, y));
        }
        updateLogs();
    }

    public void markCell(final int x, final int y) {
        minesweeperModel.markCell(x, y);
        logger.log(LogMessages.markCell(x, y));
        updateLogs();
    }

    public String getCellText(final int x, final int y) {
        if (minesweeperModel.isCellClose(x, y)) {
            if (minesweeperModel.isCellFlag(x, y)) {
                return CellText.flag.toString();
            } else if (minesweeperModel.isCellIssue(x, y)) {
                return CellText.issue.toString();
            } else {
                return CellText.close.toString();
            }
        } else if (minesweeperModel.isCellMine(x, y)) {
            return CellText.mine.toString();
        } else {
            int value = minesweeperModel.getCellValue(x, y);
            if (value == 0) {
                return "";
            } else {
                return Integer.toString(minesweeperModel.getCellValue(x, y));
            }
        }
    }

    public String getTextSmile() {
        if (minesweeperModel.isGameEnd()) {
            if (minesweeperModel.isLost()) {
                logger.log(SmilesText.dead.toString());
                updateLogs();
                return SmilesText.dead.toString();
            } else {
                logger.log(SmilesText.winner.toString());
                updateLogs();
                return SmilesText.winner.toString();
            }
        }
        logger.log(SmilesText.smile.toString());
        updateLogs();
        return SmilesText.smile.toString();
    }

    public void boardClear() {
        minesweeperModel.boardClear();
    }

    public void setMine(final int x, final int y) {
        minesweeperModel.setMine(x, y);
    }

    public String getMineCounter() {
        return Integer.toString(minesweeperModel.getMineCounter());
    }

    public int getBoardHeight() {
        return minesweeperModel.getBoardHeight();
    }

    public int getBoardWidth() {
        return minesweeperModel.getBoardWidth();
    }

    public String getFlagText() {
        return CellText.flag.toString();
    }

    public String getCloseText() {
        return CellText.close.toString();
    }

    public String getIssueText() {
        return CellText.issue.toString();
    }

    public String getMineText() {
        return CellText.mine.toString();
    }

    public String getSmileText() {
        return SmilesText.smile.toString();
    }

    public String getDeadText() {
        return SmilesText.dead.toString();
    }

    public String getWinnerText() {
        return SmilesText.winner.toString();
    }
}

final class LogMessages {
    public static final String NEW_GAME_WAS_PRESSED = "New game";
    public static String openCell(final int x, final int y) {
        return "Open cell(x:" + x + "; y:" + y + ")";
    }
    public static String markCell(final int x, final int y) {
        return "Mark cell(x:" + x + "; y:" + y + ")";
    }

    private LogMessages() { }
}
